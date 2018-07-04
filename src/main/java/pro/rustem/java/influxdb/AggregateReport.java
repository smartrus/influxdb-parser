package pro.rustem.java.influxdb;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * A class to store a report data from infulxdb
 */
@Measurement(name = "aggregateReports")
public class AggregateReport {
    @Column(name = "time")
    private Instant time;
    @Column(name = "aggregate_report_90%_line")
    private String aggregate_report_90_line;
    @Column(name = "aaggregate_report_95%_line")
    private String aaggregate_report_95_line;
    @Column(name = "aggregate_report_99%_line")
    private String aggregate_report_99_line;
    @Column(name = "aggregate_report_bandwidth")
    private String aggregate_report_bandwidth;
    @Column(name = "aggregate_report_count")
    private String aggregate_report_count;
    @Column(name = "aggregate_report_error%")
    private String aggregate_report_error;
    @Column(name = "aggregate_report_max")
    private String aggregate_report_max;
    @Column(name = "aggregate_report_median")
    private String aggregate_report_median;
    @Column(name = "aggregate_report_min")
    private String aggregate_report_min;
    @Column(name = "aggregate_report_rate")
    private String aggregate_report_rate;
    @Column(name = "aggregate_report_stddev")
    private String aggregate_report_stddev;
    @Column(name = "average")
    private String average;
    @Column(name = "buildID")
    private String buildID;
    @Column(name = "envType")
    private String envType;
    @Column(name = "projectName")
    private String projectName;
    @Column(name = "requestName")
    private String requestName;
    @Column(name = "testType")
    private String testType;
    @Column(name = "version_id")
    private String version_id;

    public Instant getTime() {
        return time;
    }

    public String getAggregate_report_90_line() {
        return aggregate_report_90_line;
    }

    public String getAaggregate_report_95_line() {
        return aaggregate_report_95_line;
    }

    public String getAggregate_report_99_line() {
        return aggregate_report_99_line;
    }

    public String getAggregate_report_bandwidth() {
        return aggregate_report_bandwidth;
    }

    public String getAggregate_report_count() {
        return aggregate_report_count;
    }

    public String getAggregate_report_error() {
        return aggregate_report_error;
    }

    public String getAggregate_report_max() {
        return aggregate_report_max;
    }

    public String getAggregate_report_median() {
        return aggregate_report_median;
    }

    public String getAggregate_report_min() {
        return aggregate_report_min;
    }

    public String getAggregate_report_rate() {
        return aggregate_report_rate;
    }

    public String getAggregate_report_stddev() {
        return aggregate_report_stddev;
    }

    public String getAverage() {
        return average;
    }

    public String getBuildID() {
        return buildID;
    }

    public String getEnvType() {
        return envType;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getTestType() {
        return testType;
    }

    public String getVersion_id() {
        return version_id;
    }
}
