package utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DataHelper {

    public static List<User> getUsers() {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            User[] users = objectMapper.readValue(new File(GlobalConstants.PROJECT_PATH + "\\src\\test\\resources\\SauceDemo.json"), User[].class);
            List<User> userList = Arrays.asList(users);

            return userList;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class User {

        private String username;

        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}
