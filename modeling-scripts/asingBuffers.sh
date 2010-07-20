#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Sets the new categories to a map.
#

# Arguments
MAP=$1
DISTANCES=$2
MAGIC_NUMBER=$3
REVERTED=$4
SUFFIX=$5

# Variables
RMAP=R_"$MAP"_"$SUFFIX"
TEMP=Temp_"$SUFFIX"
ROMAP=R_"$MAP"_"$SUFFIX"_r 

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

r.buffer input="$RMAP" output="$TEMP" distances="$DISTANCES" units=meters --quiet --overwrite;
echo r.buffer input="$RMAP" output="$TEMP" distances="$DISTANCES" units=meters --quiet --overwrite;

if [ "true" == "$REVERTED" ];
then
	echo "mapcalc"
	r.mapcalc "$ROMAP = $MAGIC_NUMBER - $TEMP ";
else
	echo "rename"
	g.rename rast="$TEMP","$ROMAP" --overwrite;
fi;

exit $RESULT
