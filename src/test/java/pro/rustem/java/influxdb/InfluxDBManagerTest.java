package pro.rustem.java.influxdb;
import org.junit.jupiter.api.*;

public class InfluxDBManagerTest {
    @Test
    public void testConnection() throws Exception {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        Boolean result = influxDBManager.connect();
        Assertions.assertTrue(result);
    }
}
