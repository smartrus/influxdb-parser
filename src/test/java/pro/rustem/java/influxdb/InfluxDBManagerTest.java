package pro.rustem.java.influxdb;
import org.influxdb.InfluxDB;
import org.junit.jupiter.api.*;

class InfluxDBManagerTest {

    @Test
    void configInitTest() {
        InfluxDBManager.configInit("./influxdb.conf");
        Assertions.assertTrue(InfluxDBManager.getInfluxDBConfig().getUrl().equals("http://localhost:8086"));
        Assertions.assertTrue(InfluxDBManager.getInfluxDBConfig().getUser().equals("root"));
        Assertions.assertTrue(InfluxDBManager.getInfluxDBConfig().getPassword().equals("root"));
        Assertions.assertTrue(InfluxDBManager.getInfluxDBConfig().getDbname().equals("jmeter"));
    }

    @Test
    void testConnection() {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        InfluxDB result = influxDBManager.connect();
        Assertions.assertNotNull(result);
    }
}