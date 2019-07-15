#!/bin/bash

pidfile=projects_ws_pid.file
test -f "$pidfile" && echo "Stopping tracker_projects" && kill $(cat "$pidfile") && rm "$pidfile"
