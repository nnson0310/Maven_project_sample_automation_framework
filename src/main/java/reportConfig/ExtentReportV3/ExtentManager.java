package reportConfig.ExtentReportV3;//package reportConfig.ExtentReportV3;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentManager {
//    private static ExtentReports extent;
//    private static String reportFileName = "ExtentReport.html";
//    private static String extentReportPath = System.getProperty("user.dir") + "/extentReportV3/" + reportFileName;
//
//    public static ExtentReports getInstance() {
//        if (extent == null)
//            createInstance();
//        return extent;
//    }
//
//    public static ExtentReports createInstance() {
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportPath);
//        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//        htmlReporter.config().setChartVisibilityOnOpen(true);
//        htmlReporter.config().setTheme(Theme.STANDARD);
//        htmlReporter.config().setDocumentTitle("Facebook HTML Report");
//        htmlReporter.config().setEncoding("utf-8");
//        htmlReporter.config().setReportName("Facebook HTML Report");
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
//        return extent;
//    }
//}