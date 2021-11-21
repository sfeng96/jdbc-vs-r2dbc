# Simple REST CURD application perf test
This project aims to compare the performance of simple REST CURD(SQL database) applications built with major web 
frameworks

 - [x] Spring Webflux + R2DBC
 - [x] Spring Webflux + JDBC (Elastic Thread Pool and BoundedElastic Thread Pool)
 - [x] Spring Boot + JDBC
 - [ ] Vert.x

## Problem Statement

Given a web application that takes a POST request and saves the json body object into a MySQL database table

I want to compare the performance between different implementations

So I can decide which approach gives the best performance and should be the way forward

## Experiment Setup

### Environment

1. Test Machine: 2020 Macbook Pro, 2.3 GHz Quad-Core Intel I7, 8 processors, 32 GB Memory
2. Test Tools: JMeter and VisualVM
3. Application running locally from IntelliJ, a MySQL database running in docker
4. Database connection pool: initial size is 50 and max is 200

### Test Setup
1. JMeter and VisualVM both running in GUI mode
2. JMeter test setup:
    * number of threads: 1000
    * ramp-up time: 20s
    * running in infinite loop, continue for 6 minutes

### Application Setup

The workflow is simple, a REST endpoint takes a POST request with a JSON body which maps to an Order object and get
saved into the database.

|Field      |Type           |Null|Key|
|-----------|---------------|----|---|
|ORDER_ID   |VARCHAR(50)    |NO  |PRI|
|PRODUCT_ID |VARCHAR(50)    |NO  |PRI|
|AMOUNT     |DECIMAL(6,2)   |YES |   |

example Order object:

```json
{
  "orderId": 1,
  "productId": 2,
  "amount": 2.03
}
```

## Key Results and Conclusion

|                               | Throughput     | max # of threads| CPU usage (approx)| Error |
|-------------------------------|----------------|-----------------|-------------------|-------|
| Webflux + R2DBC                | **1098.0/sec** | **31**          | **20%**           | 0     |
| Webflux + JDBC (Elastic)       | 734.0/sec      | 1029            | 10%               | 0     |
| Webflux + JDBC (BoundedElastic)| 723.3/sec      | 107             | 10%               | 0     |
| MVC + JDBC                    | 693.2/sec      | 221             | 10%               | 0     | 

* With a very small number of threads, pure reactive solution (Webflux + R2DBC) gave the highest throughput and 
  memory usage was also kept at a low level

## Full Results and Images
All evidence are in the *perf* repo
### JMeter Summary
Webflux + R2DBC:
![webflux + r2dbc](./perf/r2dbc/jmeter-result.png)
 Webflux + JDBC + Elastic Pool:
![webflux + jdbc + elastic pool](./perf/jdbc+elastic/jmeter-result.png)
Webflux + JDBC + BoundedElastic Pool:
![webflux + jdbc + bounded elastic pool](./perf/jdbc+bounded-elastic/jmeter-result.png)
MVC + JDBC
![mvc + jdbc](./perf/mvc-jdbc/jmeter-result.png)

### VisualVM Monitor
Webflux + R2DBC:
![r2dbc](./perf/r2dbc/visualvm-monitor.png)
Webflux + JDBC + Elastic Pool:
![jdbc + elastic pool](./perf/jdbc+elastic/visualvm-monitor.png)
Webflux + JDBC + BoundedElastic Pool:
![jdbc + bounded elastic pool](./perf/jdbc+bounded-elastic/visualvm-monitor.png)
MVC + JDBC
![mvc + jdbc](./perf/mvc-jdbc/visualvm-monitor.png)
