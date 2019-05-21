#!/bin/bash
mvn clean package
java -jar target/tracker_firebase-0.0.1-SNAPSHOT.jar & echo $! > ./firebase_ws_pid.file &
