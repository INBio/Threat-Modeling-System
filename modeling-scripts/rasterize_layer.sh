#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Convert a vector map into a raster map.
#

# Arguments
MAP=$1
SUFFIX=$2
COLUMN=$3

# Variables
VMAP=V_"$MAP"_"$SUFFIX"
RMAP=R_"$MAP"_"$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

# Import the map.
if [ "" == "$COLUMN" ] || [ "cat" == "$COLUMN" ];
then
	v.to.rast input="$VMAP"_r output="$RMAP" use=cat --overwrite --quiet;
else
	v.to.rast input="$VMAP"_r output="$RMAP" use=attr column=cat labelcolumn="$COLUMN" --overwrite --quiet;
fi

exit $RESULT;
