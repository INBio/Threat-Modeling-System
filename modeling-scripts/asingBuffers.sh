#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Sets the new categories to a map.
#

# Arguments
MAP=$1
DISTANCES=$2
SUFFIX=$3

# Variables
RMAP=R_"$MAP"_"$SUFFIX"
ROMAP=R_"$MAP"_"$SUFFIX"_r 

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$(r.buffer input="$RMAP" output="$ROMAP" distances="$DISTANCES" units=meters --quiet --overwrite);

exit $RESULT
