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
fd.flight['flightDeparture'] AS flightDeparture,
fd.flight['flightDestination'] AS flightDestination,
fd.flight['flightDate'] AS flightDate,
fd.flight['flightCompany'] + '-' +  fd.flight['flightNumber'] AS flightid
FROM flight_delay_event_raw fd;
```

### Join query
```
CREATE STREAM join_user_flight_event 
AS SELECT u.userid, f.flightStatus, f.flightDelay, 
f.flightCompanyHelpLink, 
f.flightDeparture AS flightDeparture, 
f.flightDestination AS flightDestination, 
f.flightDate AS flightDate, 
f.flightid AS flightId
FROM user_with_flight_id u
JOIN flight_delay_with_flight_id f WITHIN 10 minute ON u.flightid = f.flightid;
```

### JSON doc on output topic
```
{
    "USERID": "2d426e07-6e69-4b90-b0e8-174b319ca2d6",
    "FLIGHTSTATUS": "cancelled",
    "FLIGHTDELAY": null,
    "FLIGHTCOMPANYHELPLINK": "https://www.swiss.com/ca/en/book/flight-information/arrivals-and-departures.html",
    "FLIGHTDEPARTURE": "Paris",
    "FLIGHTDESTINATION": "St. Petersburg",
    "FLIGHTDATE": "2018-09-26T12:54:46Z",
    "FLIGHTID": "LX-739"
}
```
