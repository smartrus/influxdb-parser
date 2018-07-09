# influxdb-parser

### Build jar file

```
mvn clean install
```

### Initial setup

Create the following configuration files in the same directory as your InfluxDBParser.jar file's location.

### influxdb.conf

```
{
  "url": "http://localhost:8086",
  "user": "root",
  "password": "root",
  "dbname": "jmeter",
  "measurement": "aggregateReports",
  "field": "aggregate_report_rate",
  "retention": "autogen",
  "datadir": "/var/lib/influxdb/data/",
  "waldir": "/var/lib/influxdb/wal/",
  "out": "jmeter",
  "limit": "100"
}
```

* measurement - measurement for data format checks
* field - field for data format checks
* retention - database dump parameter
* datadir - database dump parameter
* waldir - database dump parameter
* out - database dump file name
* limit - number of points to format check.

### checks.conf

```
{
  "checks" :
    [
      {
        "check": "KB/s",
        "multiplier": "1024"
      },
      {
        "check": "/min",
        "multiplier": "0.0167"
      },
      {
        "check": "/sec",
        "multiplier": "1"
      }
    ]
}
```

### Run the jar file as follows:

```
java -jar InfluxDBParser.jar -dbconf ./influxdb.conf -chconf ./checks.conf
```
