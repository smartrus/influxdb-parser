package pro.rustem.java.influxdb;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InfluxDBManager {
    public InfluxDBManager() {
    }
    public Boolean connect() {
        Boolean result = false;
        return result;
    }

    protected InfluxDBConfig configInit() {
        InfluxDBConfig influxDBConfig = new InfluxDBConfig("http://localhost:8086", "user", "password");

        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("influxdb.conf"));
            influxDBConfig = gson.fromJson(br, InfluxDBConfig.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return influxDBConfig;
    }
}
