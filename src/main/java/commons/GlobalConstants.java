package commons;

import java.io.File;

public class GlobalConstants {
    public static final int LONG_TIME_OUT = 30;

    public static final int MIDDLE_TIME_OUT = 15;

    public static final int SHORT_TIME_OUT = 5;

    public static final int THREAD_SLEEP_MILLIS = 1000;

    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String UPLOAD_FILE_LOCATION = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;

    public static final String REPORT_NG_CAPTURED_SCREENSHOT = PROJECT_PATH + File.separator + "reportScreenshot" + File.separator + "reportNG" + File.separator;

    public static final String EXTENT_REPORT_CAPTURED_SCREENSHOT = PROJECT_PATH + File.separator + "reportScreenshot" + File.separator + "extentReport" + File.separator;

    public static final String JAVA_VERSION = System.getProperty("java.version");

}
