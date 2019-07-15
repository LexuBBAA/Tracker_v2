#!/bin/bash

pidfile=eureka_ws_pid.file
test -f "$pidfile" && echo "Stopping auth_ws" && kill $(cat "$pidfile") && rm "$pidfile"
