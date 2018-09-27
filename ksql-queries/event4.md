# KSQL queries for event #4

```
CREATE STREAM social_event_with_city 
AS SELECT s.geoLocation, s.event, s.geoLocation['city'] AS eventCity 
FROM social_event_raw s; 
```

### Join query
```
CREATE STREAM join_user_social_event 
AS SELECT u.userid, s.geoLocation['city'] AS city, s.event['eventType'] AS eventType, s.event['eventMood'] AS eventMood, s.event['eventBegin'] AS eventBegin, s.event['eventEnd'] AS eventEnd
FROM USER_WITH_FLIGHT_ID u
JOIN social_event_with_city s WITHIN 10 minute ON u.subscribedLocations = s.eventCity; 
```