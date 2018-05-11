package pro.rustem.java.influxdb;
import org.junit.jupiter.api.*;

class InfluxDBManagerTest {
    @Test
    void testConnection() {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        Boolean result = influxDBManager.connect();
        Assertions.assertTrue(result);
    }
}

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