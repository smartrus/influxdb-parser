package pro.rustem.java.influxdb;
import pro.rustem.java.cli.CliArgs;
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

import java.util.regex.*;

/**
 * Main class of the project with main method.
 */
public class InfluxDBParser{

    public static void main(String[] args) {

        // cli arguments parsing
        CliArgs cliArgs = new CliArgs(args);
        // influxdb configuration file in json format
        String dbconf = cliArgs.switchValue("-dbconf", "./influxdb.conf");
        // checks list file in json format
        String chconf = cliArgs.switchValue("-chconf", "./checks.conf");

        System.out.println(dbconf);
        System.out.println(chconf);

        // initializing db connection and checking data format
        InfluxDBParser influxDBParser = new InfluxDBParser();
        InfluxDBManager.configInit(dbconf);
        InfluxDBManager.checkInit(chconf);
        List<AggregateReport> aggregateReportList = influxDBParser.fetchAggregateReportList();
        Boolean result = influxDBParser.checkAggregateReportFormat(aggregateReportList);

        if (result) {
            System.out.println("Wrong format detected.");
            CmdExecutor cmdExecutor = new CmdExecutor();
            String command = "influx_inspect export -database " + InfluxDBManager.getInfluxDBConfig().getDbname()
                    + " -retention " + InfluxDBManager.getInfluxDBConfig().getRetention()
                    + " -datadir " + InfluxDBManager.getInfluxDBConfig().getDatadir()
                    + "-waldir " + InfluxDBManager.getInfluxDBConfig().getWaldir()
                    + " -out " + InfluxDBManager.getInfluxDBConfig().getDbname();
            cmdExecutor.executeCommand(command);
            influxDBParser.fixDumpFormat("./jmeter");
        } else {
            System.out.println("No wrong format detected.");
        }
    }

    /**
     * Fetches records from the influxdb
     * @return returns a pojo class AggregateReportList instance
     */
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

    /**
     * Checks the aggregateReport if it needs to be re-formatted
     * @param aggregateReportList
     * @return boolean value which is true if it finds any strings from checks list
     */
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

    /**
     * Fixes the dumped data by substituting strings with calculated corresponding values
     * @param dumpPath
     */
    private void fixDumpFormat(String dumpPath) {

        try {
            System.out.println("Influxdb fix started...");
            Path path = Paths.get(dumpPath);
            Stream<String> lines = Files.lines(path);
            List<Check> checks = InfluxDBManager.getChecksWrapper().getChecks();

            List<String> replaced = lines
                    .map(line-> {
                        String fixedLine = line;
                        for (Check check:
                                checks) {

                            String regexe = "([0-9]*[.])?[0-9]+" + check.getCheck();

                            Pattern pattern = Pattern.compile(regexe);
                            Matcher matcher = pattern.matcher(line);
                            while (matcher.find()) {
                                String reportValue = matcher.group(0);
                                String fixedReportValue = String.valueOf(Math.round(Float.valueOf(reportValue.substring(0, reportValue.length() - check.getCheck().length())) * check.getMultiplier()*100.0)/100.0);
                                fixedLine = line.replaceAll(reportValue, fixedReportValue);
                            }
                        }
                        return fixedLine;
                    }).collect(Collectors.toList());
            Files.write(path, replaced);

            lines.close();
            System.out.println("Influxdb fix done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
