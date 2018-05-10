package pro.rustem.java;
import org.influxdb.*;
import org.influxdb.dto.Query;

public class InfluxDBParser{

    public static void main(String[] args) {
	// write your code here
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
        String dbName = "jmeter";
        influxDB.setDatabase(dbName);
        Query query = new Query("SELECT aggregate_report_rate FROM aggregateReports LIMIT 1", dbName);
        influxDB.query(query, 20, queryResult -> System.out.println(queryResult));

        influxDB.close();
    }
}
