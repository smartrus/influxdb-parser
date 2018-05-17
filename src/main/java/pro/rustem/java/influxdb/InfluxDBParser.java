package pro.rustem.java.influxdb;
import org.influxdb.*;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InfluxDBParser{

    public static void main(String[] args) {
        InfluxDBParser influxDBParser = new InfluxDBParser();
        InfluxDBManager influxDBManager = new InfluxDBManager();
        List<AggregateReport> aggregateReportList = influxDBParser.fetchAggregateReportList();
        Boolean result = influxDBParser.checkAggregateReportFormat(aggregateReportList);

        if (result) {
            System.out.println("Wrong format detected.");
            CmdExecutor cmdExecutor = new CmdExecutor();
            // TODO need to build the command string by a method
            String command = "influx_inspect export -database jmeter -retention autogen -datadir /var/lib/influxdb/data/ -waldir /var/lib/influxdb/wal/ -out jmeter";
            cmdExecutor.executeCommand(command);
            influxDBParser.fixDumpFormat("./jmeter");
        } else {
            System.out.println("No wrong format detected.");
        }
    }

    private List<AggregateReport> fetchAggregateReportList() {
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

    private Boolean checkAggregateReportFormat(List<AggregateReport> aggregateReportList) {
        Boolean result = false;
        List<Check> checks = InfluxDBManager.getChecksWrapper().getChecks();
        System.out.println("CheckAggregateReportFormat started.");

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
        System.out.println("CheckAggregateReportFormat done.");
        return result;
    }

    private void fixDumpFormat(String dumpPath) {
        List<Check> checks = InfluxDBManager.getChecksWrapper().getChecks();

        try {
            System.out.println("Influxdb fix started...");
            Path path = Paths.get(dumpPath);
            Stream<String> lines = Files.lines(path);

            for (Check check:
                 checks) {
                List<String> replaced = lines.map(line -> line.replaceAll(check.getCheck(), "0")).collect(Collectors.toList());
                Files.write(path, replaced);
            }

            lines.close();
            System.out.println("Influxdb fix done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
