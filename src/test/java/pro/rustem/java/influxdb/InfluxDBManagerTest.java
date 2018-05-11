package pro.rustem.java.influxdb;
import org.influxdb.InfluxDB;
import org.junit.jupiter.api.*;

class InfluxDBConfigTest {
    @Test
    void getUrlTest() {
        InfluxDBConfig dbConfig = new InfluxDBConfig("http://test.com", "user", "password");
        String url = dbConfig.getUrl();
        Assertions.assertTrue(url.equals("http://test.com"));
    }

    @Test
    void getUserTest() {
        InfluxDBConfig dbConfig = new InfluxDBConfig("http://test.com", "user", "password");
        String url = dbConfig.getUser();
        Assertions.assertTrue(url.equals("user"));
    }

    @Test
    void getPasswordTest() {
        InfluxDBConfig dbConfig = new InfluxDBConfig("http://test.com", "user", "password");
        String url = dbConfig.getPassword();
        Assertions.assertTrue(url.equals("password"));
    }
}

class InfluxDBManagerTest {

    @Test
    void configInitTest() {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        InfluxDBConfig result = influxDBManager.configInit();
        Assertions.assertTrue(result.getUrl().equals("http://localhost:8086"));
        Assertions.assertTrue(result.getUser().equals("root"));
        Assertions.assertTrue(result.getPassword().equals("root"));
    }

    @Test
    void testConnection() {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        InfluxDB result = influxDBManager.connect();
        Assertions.assertNotNull(result);
    }
}