package pageObjects.bankguru;

import jdbc.DBConnect;
import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPageObject {

    WebDriver driver;

    DBConnect dbConnect;

    Connection conn = null;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public int getActorNumberFromDB(WebDriver driver) throws SQLException {
        try {
            conn = dbConnect.getDBConnection("dvdrental");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("Select count(*) from actor");
            while(result.next()) {
                System.out.println(result.getInt(1));
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return 0;
    }
}
