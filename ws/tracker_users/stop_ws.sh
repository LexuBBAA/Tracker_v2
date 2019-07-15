#!/bin/bash

pidfile=users_ws_pid.file
test -f "$pidfile" && echo "Stopping users_ws" && kill $(cat "$pidfile") && rm "$pidfile"
