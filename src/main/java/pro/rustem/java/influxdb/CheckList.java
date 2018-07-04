package pro.rustem.java.influxdb;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to store checks from a configuration file
 */
class Check {
    private String check;
    private Double multiplier;

    public String getCheck() {
        return check;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }
}

public class CheckList {
    private List<Check> checks = new ArrayList<Check>();

    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }
}
