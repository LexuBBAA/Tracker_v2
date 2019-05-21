#!/bin/bash
mvn clean package
java -jar target/tracker_auth-0.0.1-SNAPSHOT.jar & echo $! > ./auth_ws_pid.file &
