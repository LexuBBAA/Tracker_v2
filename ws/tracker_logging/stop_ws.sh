#!/bin/bash

pidfile=logging_ws_pid.file
test -f "$pidfile" && echo "Stopping logging_ws" && kill $(cat "$pidfile") && rm "$pidfile"
