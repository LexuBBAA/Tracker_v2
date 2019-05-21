#!/bin/bash

root=$PWD
script=start_ws.sh

for file in */ ; do
	cd $file
	echo "Searching for $script in directory $file"
	test -f "$script" && echo "Found $script" && /bin/bash "$script"
	cd $root
done