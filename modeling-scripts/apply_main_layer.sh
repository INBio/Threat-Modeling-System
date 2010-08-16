#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description:
# Do the map algebra: R = M1 * W1 + M2 *W2 where
# M1:	Map 1
# W1:	Relative weight of M1
# M2:	Map 2
# W2:	Relative weight of M2
# R :	Resulting map.
#

# Arguments

# layer 1 and weight
MAIN_LAYER=$1

# layer 2 and weight
RESULT_LAYER=$2

#suffix
SUFFIX=$3

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX


echo $RESULT_LAYER$TMAP
# Rounded
#r.mapcalc "$RESULT_LAYER$TMAP = if(isnull($MAIN_LAYER$RRMAP),null(),round($RESULT_LAYER$TMAP))";
# truncate
#r.mapcalc "$RESULT_LAYER$TMAP = if(isnull($MAIN_LAYER$RRMAP),null(),int($RESULT_LAYER$TMAP))";
# As Is
r.mapcalc "$RESULT_LAYER$TMAP = if(isnull($MAIN_LAYER$RMAP),null(),$RESULT_LAYER$TMAP)";
# one shot rounded
#VALOR="$RESULT_LAYER$TMAP"_2
#r.mapcalc "$VALOR = if(isnull($MAIN_LAYER$RMAP),null(),round($RESULT_LAYER$TMAP))";

exit 0;
