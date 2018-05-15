package pro.rustem.java.influxdb;
import org.influxdb.InfluxDB;
import org.junit.jupiter.api.*;

class InfluxDBManagerTest {

    @Test
    void configInitTest() {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        InfluxDBConfig result = influxDBManager.configInit();
        Assertions.assertTrue(result.getUrl().equals("http://localhost:8086"));
        Assertions.assertTrue(result.getUser().equals("root"));
        Assertions.assertTrue(result.getPassword().equals("root"));
        Assertions.assertTrue(result.getDbname().equals("jmeter"));
    }

    @Test
    void testConnection() {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        InfluxDB result = influxDBManager.connect();
        Assertions.assertNotNull(result);
    }
}