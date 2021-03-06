package pro.rustem.java.influxdb;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to store influxdb configuration
 */
public class InfluxDBConfig {
    private String url;
    private String user;
    private String password;
    private String dbname;
    private String field;
    private String measurement;
    private String retention;
    private String datadir;
    private String waldir;
    private String out;
    private String limit;

    public InfluxDBConfig(String url, String user, String password, String dbname, String field, String measurement,
                          String retention, String datadir, String waldir, String out, String limit) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.dbname = dbname;
        this.field = field;
        this.measurement = measurement;
        this.retention = retention;
        this.datadir = datadir;
        this.waldir = waldir;
        this.out = out;
        this.limit = limit;
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

    public String getField() {
        return field;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getRetention() {
        return retention;
    }

    public String getDatadir() {
        return datadir;
    }

    public String getWaldir() {
        return waldir;
    }

    public String getOut() {
        return out;
    }

    public String getLimit() {
        return limit;
    }
}