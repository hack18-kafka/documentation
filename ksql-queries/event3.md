# KSQL queries for event #4

```
CREATE STREAM location_with_city
AS SELECT g.userId, g.geoLocation, g.geoLocation['city'] AS locationCity 
FROM geo_loc_event_raw g; 
```

### Join query
```
CREATE STREAM join_location_weather_event 
AS SELECT g.userid, 
w.geoLocation['city'] AS city, 
w.warning['warningType'] AS weatherType, 
w.warning['warningMessage'] AS weatherMessage, 
w.warning['warningBegin'] AS weatherBegin, 
w.warning['warningEnd'] AS weatherEnd
FROM location_with_city g
JOIN weather_forecast_with_city w WITHIN 10 minute ON g.locationCity = w.weatherCity;
```
