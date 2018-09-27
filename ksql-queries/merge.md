# KSQL queries to merge streams
Some join streams produce JSON docs with the same structure and can thus be merged.

## Merge event #2 and event #4
```
CREATE STREAM merge_final_social_streams AS SELECT * 
FROM JOIN_USER_SOCIAL_EVENT, JOIN_LOCATION_SOCIAL_EVENT; 
```

## Merge event #3 and event #5 
```
CREATE STREAM merge_final_weather_streams AS SELECT * 
FROM JOIN_USER_WEATHER_EVENT, JOIN_LOCATION_WEATHER_EVENT; 
```