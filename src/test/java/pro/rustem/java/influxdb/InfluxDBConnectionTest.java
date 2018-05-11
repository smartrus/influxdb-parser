package pro.rustem.java.influxdb;
import org.junit.jupiter.api.*;

public class InfluxDBConnectionTest {
    @Test
    public void testInfluxDBConnection() throws Exception {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        Boolean result = influxDBManager.connect();
        Assertions.assertTrue(result);
    }
}
