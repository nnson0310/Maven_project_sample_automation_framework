package utilities;

import com.github.javafaker.Faker;

public class DataFaker {

    private Faker faker;

    public static DataFaker getDataFaker() {
        return new DataFaker();
    }

    public DataFaker() {
       faker = new Faker();
    }

    public String getFirstName() {
        return this.faker.name().firstName();
    }

    public String getLastName() {
        return this.faker.name().lastName();
    }

    public String getEmail() {
        return this.faker.internet().emailAddress();
    }
}
