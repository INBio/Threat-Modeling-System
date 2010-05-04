#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Return the type of the shapefile inferenced acording to the
# topological information grass returns with the command v.info.
#

# Arguments
MAP=$1
SUFFIX=$2

# Variables
VMAP=V_"$MAP"_"$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$(
v.info -t map=$VMAP | \
egrep "points|lines|areas" | \
awk '{ 
		split($1, array, "=");
		if(array[2] != 0)
			print array[1]; 
	 }'	);

echo $RESULT

exit;
