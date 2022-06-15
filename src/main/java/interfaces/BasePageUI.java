package interfaces;

public class BasePageUI {

    public static final String USER_DYNAMIC_NAVIGATION_LINK = "xpath=//div[contains(@class, '%s')]//a[text()='%s']";

    public static final String DYNAMIC_INPUT_TEXTBOX = "xpath=//input[@name='%s']";

    public static final String DYNAMIC_SELECT = "xpath=//select[@name='%s']";

    public static final String DYNAMIC_RADIO_CHECKBOX = "xpath=//input[@type='radio' and @id='%s']";

    public static final String DYNAMIC_BUTTON_BY_ID = "xpath=//button[contains(@id, '%s')]";

}
