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
INTERVAL_QUANTITY=$4
SUFFIX=$5

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

TEMPORAL_MAP="temp_$SUFFIX"

r.neighbors -c input=$LAYER$RMAP output=$TEMPORAL_MAP method=sum size=$RADIUS  --quiet --overwrite
r.mapcalc "$LAYER$RRMAP = ($TEMPORAL_MAP/$RADIO_IN_METERS)*10000";

RANGE=$(r.info map=$LAYER$RRMAP -r);
MIN=$(echo $RANGE |sed "s/ /=/g" | awk -F= '{ printf "%s",$2}')
MAX=$(echo $RANGE |sed "s/ /=/g" | awk -F= '{ printf "%s",$4}');
SIZE=$(echo "($MAX-$MIN)/$INTERVAL_QUANTITY" | bc );

echo "#--> $MIN $MAX $SIZE"
r.mapcalc "$LAYER$RRMAP=(($LAYER$RRMAP-$MIN)/$SIZE);"
r.mapcalc "$LAYER$RRMAP=if($LAYER$RRMAP==int($LAYER$RRMAP),$LAYER$RRMAP,int($LAYER$RRMAP)+1);"
r.mapcalc "$LAYER$RRMAP=if($LAYER$RRMAP==0,1,$LAYER$RRMAP)"
exit 0;

