package envConfig;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${envName}.properties")
public interface Environment extends Config {

    @Key("app.url")
    String appUrl();

    @Key("app.database.username")
    String databaseUsername();

    @Key("app.database.password")
    String databasePassword();

}
