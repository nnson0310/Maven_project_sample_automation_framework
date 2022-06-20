package commons;

import commons.envFactory.CloudEnvFactory;
import commons.envFactory.GridEnvFactory;
import commons.envFactory.LocalEnvFactory;
import commons.envFactory.SERVER_NAME;
import envConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    /*
    * Headless browser ám chỉ việc trình duyệt được chạy ngầm mà k hiển
    * thị giao diện. Chủ yếu dùng trong
    * + test UI frontend
    * + crawl data (data analyst...)
    * */
    private WebDriver driver;

    protected final Log log;

    private String projectPath = System.getProperty("user.dir");

    public BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public static void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getBrowserDriver(
            String browserName,
            String envName,
            String ipAddress,
            String port,
            String os,
            String osVersion,
            String browserVersion
    ) {
        switch(envName) {
            case "grid":
                driver = new GridEnvFactory(browserName, ipAddress, port).getDriver();
                break;
            case "cloud":
                driver = new CloudEnvFactory(browserName, os, osVersion, browserVersion).getDriver();
                break;
            default:
                driver = new LocalEnvFactory(browserName).getDriver();
        }

        String url = "https://www.saucedemo.com/";

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        return driver;
    }

    protected String getEnvironmentUrl() {
        String envName = System.getProperty("env");

        if (envName == null || envName.equals("")) {
            envName = "dev";
        }
        ConfigFactory.setProperty("envName", envName);

        Environment environment = ConfigFactory.create(Environment.class);

        return environment.appUrl();
    }

    //Custom Assert
    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        System.out.println("Verification Start");
        try {
            System.out.println(" -------------------------- PASSED -------------------------- ");
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        System.out.println("Verification Start");
        try {
            System.out.println(" -------------------------- PASSED -------------------------- ");
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        System.out.println("Verification Start");
        try {
            Assert.assertEquals(actual, expected);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    @BeforeSuite
    public void deleteAllureReport() {
        deleteAllFileInFolder("/allure-json");
    }

    public void deleteAllFileInFolder(String reportName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + reportName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }


    protected String getCurrentDay() {
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            String dayValue = "0" + day;
            return dayValue;
        }
        return String.valueOf(day);
    }

    protected String getCurrentMonth() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        int month = now.getMonthOfYear();
        if (month < 10) {
            String monthValue = "0" + month;
            return monthValue;
        }
        return String.valueOf(month);
    }

    protected String getCurrentYear() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return String.valueOf(now.getYear());
    }

    /**
     * Get today with format
     * @return today
     */
    protected String getToday() {
        String today = getCurrentMonth() + "/" + getCurrentDay() + "/" + getCurrentYear();
        System.out.println(today);
        return today;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void closeBrowserAndKillProcess() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //    protected WebDriver getBrowserDriverBySaucelabs(
//            String pageUrl,
//            String browserName,
//            String platform,
//            String browserVersion
//    ) {
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", platform);
//        capabilities.setCapability("browserName", browserName);
//        capabilities.setCapability("browserVersion", browserVersion);
//        Map<String, Object> sauceOptions = new HashMap<>();
//        sauceOptions.put("name", "Run on Saucelabs on " + platform + " and on " + browserName);
//        capabilities.setCapability("sauce:options", sauceOptions);
//
//        RemoteWebDriver driver = null;
//        try {
//            driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCELABS_URL), capabilities);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get(pageUrl);
//        return driver;
//    }
}
