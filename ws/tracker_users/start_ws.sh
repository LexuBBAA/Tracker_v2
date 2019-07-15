#!/bin/bash
mvn clean package
java -jar target/tracker_users-0.0.1-SNAPSHOT.jar & echo $! > ./users_ws_pid.file &
