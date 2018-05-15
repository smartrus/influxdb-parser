package pro.rustem.java.influxdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckListTest {

    @Test
    void getChecks() {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        CheckList result = influxDBManager.checkInit();
        Assertions.assertTrue(result.getChecks().get(0).getCheck().equals("KB/s"));
    }
}