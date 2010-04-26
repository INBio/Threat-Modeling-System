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
# RESMAP="RES_$SUFFIX"
RESMAP=R_"$6"_"$SUFFIX"

# Raster maps names
RMAP1=R_"$M1"_"$SUFFIX"_r
RMAP2=R_"$M2"_"$SUFFIX"_r

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

# Import the map.
RESULT=$(r.mapcalc "$RESMAP = if(isnull($RMAP1),0,$RMAP1)*$WEIGHT_M1 + if(isnull($RMAP2),0,$RMAP2)*$WEIGHT_M2");
RESULT=$(r.null map=$RESMAP setnull=0 --quiet)

exit $RESULT;
