package pro.rustem.java.influxdb;
import org.influxdb.*;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;

public class InfluxDBParser{

    public static void main(String[] args) {
        InfluxDBManager influxDBManager = new InfluxDBManager();
        List<AggregateReport> aggregateReportList = InfluxDBParser.fetchAggregateReportList();
        Boolean result = InfluxDBParser.checkAgregateReportFormat(aggregateReportList);
        System.out.println(result);
    }

    private static List<AggregateReport> fetchAggregateReportList() {
        InfluxDB influxDB = InfluxDBManager.connect();
        String dbname = InfluxDBManager.getInfluxDBConfig().getDbname();
        influxDB.setDatabase(dbname);
        Query query = new Query("SELECT * FROM aggregateReports LIMIT 100", dbname);

        QueryResult queryResult = influxDB.query(query);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<AggregateReport> aggregateReportList = resultMapper
                .toPOJO(queryResult, AggregateReport.class);

        influxDB.close();
        return aggregateReportList;
    }

    private static Boolean checkAgregateReportFormat(List<AggregateReport> aggregateReportList) {
        Boolean result = false;
        List<Check> checks = InfluxDBManager.getChecksWrapper().getChecks();

        for (AggregateReport aggregateReport:
        aggregateReportList) {
            for (Check check:
                 checks) {
                if (aggregateReport.getAggregate_report_rate() != null &&
                        aggregateReport.getAggregate_report_rate().contains(check.getCheck())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}
