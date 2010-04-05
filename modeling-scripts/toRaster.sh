#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Convert a vector map into a raster map.
#

# Arguments
MAP=$1
SUFFIX=$2

# Variables
VMAP="V_$(basename $MAP .shp)_$SUFFIX"
RMAP="R_$(basename $MAP .shp)_$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

# Import the map.
RESULT=$(v.to.rast input=$VMAP output=$RMAP use=cat --quiet);

exit $RESULT;
