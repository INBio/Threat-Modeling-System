#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Sets the new categories to a map.
#

# Arguments
LAYER=$1
RADIUS=$2
RADIO_IN_METERS=$3
SUFFIX=$4

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

TEMPORAL_MAP="temp_$SUFFIX"

r.neighbors -c input=$LAYER$RMAP output=$TEMPORAL_MAP method=sum size=$RADIUS  --quiet --overwrite
r.mapcalc "$LAYER$RRMAP = ($TEMPORAL_MAP/$RADIO_IN_METERS)*100";

exit 0;
