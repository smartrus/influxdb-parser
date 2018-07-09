package pro.rustem.java.influxdb;
import com.google.gson.Gson;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class for managing influxdb essential operations and initializing configuration items
 */
public class InfluxDBManager {
    private static InfluxDBConfig influxDBConfig;
    private static CheckList checksWrapper;

    public static InfluxDB connect() {
        InfluxDB influxDB = InfluxDBFactory.connect(influxDBConfig.getUrl(), influxDBConfig.getUser(),
                influxDBConfig.getPassword());
        return influxDB;
    }

    public static void configInit(String dbconf) {

        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dbconf));
            InfluxDBManager.influxDBConfig = gson.fromJson(br, InfluxDBConfig.class);
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
    }

    public static void checkInit(String chconf) {
        CheckList checks = new CheckList();
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(chconf));
            InfluxDBManager.checksWrapper = gson.fromJson(br, CheckList.class);
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
    }

    public static InfluxDBConfig getInfluxDBConfig() {
        return influxDBConfig;
    }

    public static CheckList getChecksWrapper() {
        return checksWrapper;
    }
}
