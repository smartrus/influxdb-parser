package pro.rustem.java.influxdb;

import java.util.ArrayList;
import java.util.List;

public class InfluxDBConfig {
    private String url;
    private String user;
    private String password;
    private String dbname;

    public InfluxDBConfig(String url, String user, String password, String dbname) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.dbname = dbname;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDbname() { return dbname; }
}