#!/bin/bash
mvn clean package
java -jar target/tracker_logging-0.0.1-SNAPSHOT.jar & echo $! > ./logging_ws_pid.file &
