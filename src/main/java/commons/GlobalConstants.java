package commons;

import java.io.File;

public class GlobalConstants {
    public static final int LONG_TIME_OUT = 30;

    public static final int MIDDLE_TIME_OUT = 15;

    public static final int SHORT_TIME_OUT = 5;

    public static final int THREAD_SLEEP_MILLIS = 1000;

    public static final String OS_NAME = System.getProperty("os.name");

    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String UPLOAD_FILE_LOCATION = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;

    public static final String DOWNLOAD_FILE_LOCATION = PROJECT_PATH + File.separator + "downloadedFiles" + File.separator;

    public static final String REPORT_NG_CAPTURED_SCREENSHOT = PROJECT_PATH + File.separator + "reportScreenshot" + File.separator + "reportNG" + File.separator;

    public static final String EXTENT_REPORT_CAPTURED_SCREENSHOT = PROJECT_PATH + File.separator + "reportScreenshot" + File.separator + "extentReport" + File.separator;

    public static final String JAVA_VERSION = System.getProperty("java.version");

    public static final String BROWSER_LOGS_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;

    //BrowserStack
    public static final String BROWSER_STACK_USERNAME = "thuminh_bDCM2T";
    public static final String BROWSER_STACK_PASSWORD = "TpZezLKqGvzcYjhvXmDW";
    public static final String BROWSER_STACK_URL = "https://" + BROWSER_STACK_USERNAME + ":" + BROWSER_STACK_PASSWORD + "@hub-cloud.browserstack.com/wd/hub";

    // Saucelabs account
    public static final String SAUCELABS_USERNAME = "oauth-tomanyeuem123-029fd";
    public static final String SAUCELABS_PASSWORD = "f9cddf50-6249-40da-b489-c5ca465ab9c8";
    public static final String SAUCELABS_URL = "https://" + SAUCELABS_USERNAME + ":" + SAUCELABS_PASSWORD + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";

}
