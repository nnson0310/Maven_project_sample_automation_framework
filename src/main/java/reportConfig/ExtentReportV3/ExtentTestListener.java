package reportConfig.ExtentReportV3;//package reportConfig.ExtentReportV3;
//
//import java.io.IOException;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//
//import commons.BaseTest;
//
//public class ExtentTestListener extends BaseTest implements ITestListener {
//    private static ExtentReports extent = ExtentManager.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
//
//    @Override
//    public synchronized void onStart(ITestContext context) {
//    }
//
//    @Override
//    public synchronized void onFinish(ITestContext context) {
//        extent.flush();
//    }
//
//    @Override
//    public synchronized void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
//        test.set(extentTest);
//    }
//
//    @Override
//    public synchronized void onTestSuccess(ITestResult result) {
//        test.get().pass("Test passed");
//    }
//
//    @Override
//    public synchronized void onTestFailure(ITestResult result) {
//        Object testClass = result.getInstance();
//        WebDriver driver = ((BaseTest) testClass).getDriver();
//        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//
//        try {
//            test.get().fail(result.getThrowable());
//            test.get().log(Status.FAIL, "Test failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public synchronized void onTestSkipped(ITestResult result) {
//        test.get().skip(result.getThrowable());
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//
//    }
//}