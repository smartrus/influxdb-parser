package pro.rustem.java;
import org.influxdb.*;
import org.influxdb.InfluxDB.LogLevel;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.BoundParameterQuery.QueryBuilder;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Series;
import org.influxdb.impl.InfluxDBImpl;

public class InfluxDBParser{

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World!");
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
        String dbName = "jmeter";
        influxDB.setDatabase(dbName);
        Query query = new Query("SELECT aggregate_report_rate FROM aggregateReports LIMIT 1", dbName);
        influxDB.query(query, 20, queryResult -> System.out.println(queryResult));

        influxDB.close();
    }
}
