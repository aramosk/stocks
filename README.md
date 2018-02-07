# Stocks application

This is a spring-boot application with a RESTful api that provides CRUD operations over stocks with the following endpoints:

* GET /api/stocks (get a list of stocks)
* GET /api/stocks/1 (get one stock from the list) 
* PUT /api/stocks/1 (update the price of a single stock)
* POST /api/stocks (create a stock) 
* DELETE /api/stocks/1 (delete a singlestock)

It includes an Angular and jQuery frontend application

## How to install

* Clone this repository 
* JDK 1.8 and Maven 3.x is required
* Build the project and run the tests by running ```mvn clean install```

## How to Run 

This application is packaged as a jar with Tomcat 8 embedded. 

You can run it by using:
```
        java -jar -Dspring.profiles.active=dev target/stocksApp-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=dev"
```

### GET /api/stocks (get a list of stocks)

```
$ curl http://localhost:8080/api/stocks

[
    {
        "id": 1,
        "name": "facebook",
        "currentPrice": 5000.75,
        "lastUpdate": 1515756814972
    },
    {
        "id": 2,
        "name": "yahoo",
        "currentPrice": 1500.85,
        "lastUpdate": 1515756814976
    },
    {
        "id": 3,
        "name": "google",
        "currentPrice": 2000.95,
        "lastUpdate": 1515756814976
    },
    {
        "id": 4,
        "name": "amazon",
        "currentPrice": 2500.6,
        "lastUpdate": 1515756814976
    },
    {
        "id": 5,
        "name": "uber",
        "currentPrice": 3000.5,
        "lastUpdate": 1515756814976
    }
]

```

### GET /api/stocks/1 (get one stock from the list)

```
$ curl http://localhost:8080/api/stocks/1

{
    "id": 1,
    "name": "facebook",
    "currentPrice": 5000.75,
    "lastUpdate": 1515761693510
}

```

### POST /api/stocks (create a stock)


```

curl -i -X POST -H "Content-Type:application/json" -d "{  \"name\" : \"airbnb\",  \"currentPrice\" : 4500 }" http://localhost:8080/api/stocks

HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 12 Jan 2018 14:05:45 GMT

{"id":6,"name":"airbnb","currentPrice":4500,"lastUpdate":1515765945111}

```

### PUT /api/stocks/1 (update the price of a single stock)

```
$  curl -i -X PUT -H "Content-Type:application/json" -d "{  \"currentPrice\" : 5500.90 }" http://localhost:8080/api/stocks/1
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 12 Jan 2018 14:39:52 GMT

{"id":1,"name":"facebook","currentPrice":5500.90,"lastUpdate":1515767881444}

```

### DELETE /api/stocks/1 (delete a single stock)

```

curl -X DELETE http://localhost:8080/api/stocks/2

``` 
