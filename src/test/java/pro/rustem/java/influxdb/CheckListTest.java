package pro.rustem.java.influxdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckListTest {

    @Test
    void getChecks() {
        InfluxDBManager.checkInit("./checks.conf");
        Assertions.assertTrue(InfluxDBManager.getChecksWrapper().getChecks().get(0).getCheck().equals("KB/s"));
    }
}