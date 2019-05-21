#!/bin/bash
mvn clean package
java -jar target/EurekaServer-0.0.1-SNAPSHOT.jar & echo $! > ./eureka_ws_pid.file &
