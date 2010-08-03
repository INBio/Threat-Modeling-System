#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Sets the new categories to a map.
#

# Arguments
LAYER=$1
DISTANCES=$2
MAGIC_NUMBER=$3
REVERTED=$4
SUFFIX=$5

# Variables
TEMP=Temp_"$SUFFIX"

RMAP=R_"$MAP"_"$SUFFIX"
ROMAP=R_"$MAP"_"$SUFFIX"_r 

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

echo "$GISRC"

r.buffer input=$LAYER$RMAP output=$TEMP distances="$DISTANCES" units=meters --quiet --overwrite;

if [ "true" == "$REVERTED" ];
then
	r.mapcalc "$LAYER$RRMAP = $MAGIC_NUMBER - $TEMP ";
else
	g.rename rast="$TEMP","$LAYER$RRMAP" --overwrite;
fi;

exit $RESULT
