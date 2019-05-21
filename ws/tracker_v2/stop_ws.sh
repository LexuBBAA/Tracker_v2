#!/bin/bash

pidfile=tracker_v2_ws_pid.file
test -f "$pidfile" && echo "Stopping tracker_v2" && kill $(cat "$pidfile") && rm "$pidfile"
