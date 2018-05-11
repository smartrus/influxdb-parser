package pro.rustem.java.influxdb;
import com.google.gson.Gson;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InfluxDBManager {
    public InfluxDBManager() {
    }

    public InfluxDB connect() {
        InfluxDBConfig influxDBConfig = this.configInit();
        InfluxDB influxDB = InfluxDBFactory.connect(influxDBConfig.getUrl(), influxDBConfig.getUser(),
                influxDBConfig.getPassword());
        return influxDB;
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

    protected CheckList checkInit() {
        CheckList checks = new CheckList();
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("checks.conf"));
            checks = gson.fromJson(br, CheckList.class);
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

        return checks;
    }
}
