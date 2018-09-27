# KSQL queries for event #1
```
CREATE STREAM user_with_flight_id
AS SELECT uwf.userId,
uwf.firstname,
uwf.lastname,
uwf.flight['company'] AS company,
uwf.flight['number'] AS number,
uwf.flight['flightDeparture'] AS flightDeparture,
uwf.flight['flightDestination'] AS flightDestination,
uwf.flight['flightDate'] AS flightDate,
uwf.subscribedLocations,
uwf.flight['company'] + '-' +  uwf.flight['number'] AS flightid
FROM user_profile_with_flight uwf;
```

```
CREATE STREAM flight_delay_with_flight_id
AS SELECT fd.flightStatus,
fd.flightDelay,
fd.flightCompanyHelpLink,
fd.flight['flightCompany'] AS flightCompany,
fd.flight['flightNumber'] AS flightNumber,
fd.flight['flightDestination'] AS flightDestination,
fd.flight['flightDate'] AS flightDate,
fd.flight['flightCompany'] + '-' +  fd.flight['flightNumber'] AS flightid
FROM flight_delay_event_raw fd;
```

### Join query
```
CREATE STREAM join_user_flight_event 
AS SELECT u.userid, f.flightStatus, f.flightDelay, f.flightCompanyHelpLink, f.flightDestination, f.flightDate, f.flightid
FROM user_with_flight_id u
JOIN flight_delay_with_flight_id f WITHIN 10 minute ON u.flightid = f.flightid;
```
