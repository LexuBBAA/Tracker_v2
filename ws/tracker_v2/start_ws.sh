#!/bin/bash
mvn clean package
java -jar target/tracker_v2-0.0.1-SNAPSHOT.jar & echo $! > ./tracker_v2_ws_pid.file &
