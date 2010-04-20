#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# return the minimun and the maximun values of a raster map.
# in the format:  "min:max"

# Arguments
MAP=$1
SUFFIX=$2

# Variables
<<<<<<< HEAD
RMAP="R_$(basename $MAP .shp)_$SUFFIX"
=======
RMAP=R_"$MAP"_"$SUFFIX"
>>>>>>> next

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RANGE=$(r.info map=$RMAP -r)

RESULT=$(echo $RANGE |sed "s/ /=/g" | awk -F= '{ printf "%s:%s",$2,$4}')

echo $RESULT

exit;
