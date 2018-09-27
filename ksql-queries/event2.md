# KSQL queries for event #2

```
#CREATE STREAM location_with_city
AS SELECT g.userId, g.geoLocation, g.geoLocation['city'] AS locationCity 
FROM geo_loc_event_raw g;
```

### Join query
```
CREATE STREAM join_location_social_event 
AS SELECT g.userid, 
s.geoLocation['city'] AS city, 
s.event['eventType'] AS eventType, 
s.event['eventMood'] AS eventMood, 
s.event['eventBegin'] AS eventBegin, 
s.event['eventEnd'] AS eventEnd
FROM location_with_city g
JOIN social_event_with_city s WITHIN 10 minute ON g.locationCity = s.eventCity;
```
