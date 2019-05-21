#!/bin/bash

pidfile=firebase_ws_pid.file
test -f "$pidfile" && echo "Stopping firebase_ws" && kill $(cat "$pidfile") && rm "$pidfile"
