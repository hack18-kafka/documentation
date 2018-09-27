# KSQL queries for event #4

```
CREATE STREAM social_event_with_city 
AS SELECT s.geoLocation, s.event, s.geoLocation['city'] AS eventCity 
FROM social_event_raw s; 
```

### Join query
```
CREATE STREAM join_user_social_event 
AS SELECT u.userid, 
s.geoLocation['city'] AS city, 
s.event['eventType'] AS eventType, 
s.event['eventMood'] AS eventMood, 
s.event['eventBegin'] AS eventBegin, 
s.event['eventEnd'] AS eventEnd
FROM USER_WITH_FLIGHT_ID u
JOIN social_event_with_city s WITHIN 10 minute ON u.subscribedLocations = s.eventCity; 
```

### JSON doc on output topic
```
{
    "USERID": "c9225e8c-f79b-4306-a850-c5f6d0b0e1bf",
    "CITY": "Lyon",
    "EVENTTYPE": "carnival",
    "EVENTMOOD": "1",
    "EVENTBEGIN": "2018-09-26T16:46:08Z",
    "EVENTEND": "2018-09-28T15:53:03Z"
}
```