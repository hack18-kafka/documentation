### Merge query event2 & event4
```
CREATE STREAM merge_social_streams AS SELECT * 
FROM JOIN_USER_SOCIAL_EVENT, JOIN_LOCATION_SOCIAL_EVENT;
```
