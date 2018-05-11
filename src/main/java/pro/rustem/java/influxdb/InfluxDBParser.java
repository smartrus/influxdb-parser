package pro.rustem.java.influxdb;
import org.influxdb.*;
import org.influxdb.dto.Query;

public class InfluxDBParser{

    public static void main(String[] args) {
	// write your code here
        InfluxDBManager influxDBManager = new InfluxDBManager();
        InfluxDB influxDB = influxDBManager.connect();
        influxDB.setDatabase("jmeter");
        Query query = new Query("SELECT aggregate_report_rate FROM aggregateReports LIMIT 1", "jmeter");
        influxDB.query(query, 20, queryResult -> System.out.println(queryResult));

        influxDB.close();
    }

}
