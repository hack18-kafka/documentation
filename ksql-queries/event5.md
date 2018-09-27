# KSQL queries for event #5

``` 
CREATE STREAM weather_forecast_with_city 
AS SELECT w.geoLocation, w.warning, w.geoLocation['city'] AS weatherCity 
FROM weather_forecast_event_raw w;
```

### Join query
```
CREATE STREAM join_user_weather_event 
AS SELECT u.userid, 
w.geoLocation['city'] AS city, 
w.warning['warningType'] AS weatherType, 
w.warning['warningMessage'] AS weatherMessage, 
w.warning['warningBegin'] AS weatherBegin, 
w.warning['warningEnd'] AS weatherEnd
FROM USER_WITH_FLIGHT_ID u
JOIN weather_forecast_with_city w WITHIN 10 minute ON u.subscribedLocations = w.weatherCity; 
```

### JSON doc on output topic
```
{
    "USERID": "2d426e07-6e69-4b90-b0e8-174b319ca2d6",
    "CITY": "Berlin",
    "WEATHERTYPE": "earthquake",
    "WEATHERMESSAGE": "Wedding Song, The ",
    "WEATHERBEGIN": "2018-09-26T06:45:08Z",
    "WEATHEREND": "2018-09-29T15:50:24Z"
}
```