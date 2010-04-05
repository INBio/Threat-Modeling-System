#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Do the map algebra: R = M1 * W1 + M2 *W2 where
# M1:	Map 1
# W1:	Relative weight of M1
# M2:	Map 2
# W2:	Relative weight of M2
# R	:	Resulting map.
#

# Arguments
SUFFIX=$1

# Map 1 and weight
M1=$2
WEIGHT_M1=$3

# Map 2 and weight
M2=$4
WEIGHT_M2=$5

# Variables
RESMAP="RES_$SUFFIX"

# Raster maps names
RMAP1="R_$(basename $M1 .shp)_$SUFFIX"
RMAP2="R_$(basename $M2 .shp)_$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

# Import the map.
RESULT=$(r.mapcalc "$RESMAP = $RMAP1*$WEIGHT_M1 + $RMAP2*$WEIGHT_M2");

exit $RESULT;
