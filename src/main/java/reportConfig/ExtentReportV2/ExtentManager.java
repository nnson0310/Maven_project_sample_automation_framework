package reportConfig.ExtentReportV2;//package reportConfig.ExtentReportV2;
//
//import com.relevantcodes.extentreports.ExtentReports;
//
//public class ExtentManager {
//
//    private static ExtentReports extent;
//
//    public synchronized static ExtentReports getReporter() {
//        if (extent == null) {
//            extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReports/ExtentReportResults.html", true);
//        }
//        return extent;
//    }
//}