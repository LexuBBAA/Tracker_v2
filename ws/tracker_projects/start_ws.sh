#!/bin/bash
mvn clean package
java -jar target/tracker_projects-0.0.1-SNAPSHOT.jar & echo $! > ./projects_ws_pid.file &
