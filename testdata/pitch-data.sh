#!/usr/bin/bash
# push data to topics for AXA Hackathon 2018 pitch

echo "START"

echo "pushing FLIGHT DELAY"
/home/centos/confluent-5.0.0/bin/kafka-console-producer --broker-list kafka.service.ocean:9092 -topic flight_delay_event_raw <<EOF
{"flight":{"flightCompany":"LX","flightNumber":1111,"flightDeparture":"Zurich","flightDestination":"Munchen","flightDate":"2018-09-27T22:05:01Z"},"flightStatus":"delayed","flightDelay":60}
EOF
echo "FLIGHT DELAY pushed"

echo "***************************"
echo "release HAIL - ENTER"
read foo

echo "pushing HAIL"
/home/centos/confluent-5.0.0/bin/kafka-console-producer --broker-list kafka.service.ocean:9092 -topic weather_forecast_event_raw <<EOF
{"geoLocation":{"country":"Switzerland","city":"Zurich","longitude":8.55,"latitude":47.36667},"warning":{"warningType":"hail","warningMessage":"strong hail in Zurich! Be careful!","warningBegin":"2018-09-27T16:32:26Z"}}
EOF
echo "HAIL pushed"

echo "***************************"
echo "release OKTOBERFEST - ENTER"
read foo

echo "pushing OKTOBERFEST"
/home/centos/confluent-5.0.0/bin/kafka-console-producer --broker-list kafka.service.ocean:9092 -topic social_event_raw <<EOF
{"geoLocation":{"country":"Germany","city":"Munchen","longitude":11.5761249,"latitude":48.137154},"event":{"eventType":"festivity","eventMood": 1,"eventBegin":"2018-09-27T09:43:53Z","eventEnd":"2018-10-11T21:07:29Z"}}
EOF
echo "OKTOBERFEST pushed"

echo "***************************"
echo "finished"


