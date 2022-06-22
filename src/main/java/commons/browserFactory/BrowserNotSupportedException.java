package commons.browserFactory;

public class BrowserNotSupportedException extends IllegalStateException {

    public BrowserNotSupportedException(String browserName) {
        super(String.format("%s is not supported on " + System.getProperty("os.name"), browserName));
    }
}
