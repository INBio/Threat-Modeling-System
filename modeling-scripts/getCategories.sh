#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Returns a list of the differents categories of a raster map.
#

# Arguments
MAP=$1
SUFFIX=$2

# Variables
VMAP="V_$(basename $MAP .shp)_$SUFFIX"_r

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$( v.report map=$VMAP option=area units=k | awk -F'|' '{ printf "%s:%s\r",$1,$2}');

echo $RESULT

exit;
