#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Do the map algebra: R = M1 * W1 + M2 *W2 where
# M1:	Map 1
# W1:	Relative weight of M1
# M2:	Map 2
# W2:	Relative weight of M2
# R :	Resulting map.
#

# Arguments
TMAP=$1
SMAP=$2
SUFFIX=$3

# Variables
# RESMAP="RES_$SUFFIX"
RESMAP=R_Final_"$SUFFIX"_r

# Raster maps names
RTMAP=R_"$TMAP"_"$SUFFIX"_r
RSMAP=R_"$SMAP"_"$SUFFIX"_r

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

# Import the map.
RESULT=$(r.mapcalc "$RESMAP = $RTMAP - $RSMAP");

exit $RESULT;
