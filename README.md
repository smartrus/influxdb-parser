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
  "url": "http://your_domain:8086",
  "user": "user",
  "password": "your_password",
  "dbname": "jmeter"
}
```

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
java -jar InfluxDBParser.jar
```
