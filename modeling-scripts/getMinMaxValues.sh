#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# exports a resulting map into a png image file format

# Arguments
MAP=$1
SUFFIX=$2

# Variables
RMAP="R_$(basename $MAP .shp)_$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RANGE=$(r.info map=$RMAP -r)

RESULT=$(echo $RANGE |sed "s/ /=/g" | awk -F= '{ printf "%s:%s",$2,$4}')

echo $RESULT

exit;
