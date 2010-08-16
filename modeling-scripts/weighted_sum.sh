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
LAYER1=$1
WEIGHT_L1=$2

# layer 2 and weight
LAYER2=$3
WEIGHT_L2=$4

# output layer name
OUTPUT_LAYER=$5

#suffix
SUFFIX=$6

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX


if [ "$WEIGHT_L1" == "1.0" ];
then 
	MAP1=$LAYER1$TMAP
else
	MAP1=$LAYER1$RRMAP
fi;


if [ "$WEIGHT_L2" == "1.0" ];
then 
	MAP2=$LAYER2$TMAP
else
	MAP2=$LAYER2$RRMAP
fi;

# do the math
r.mapcalc "$OUTPUT_LAYER$TMAP = if(isnull($MAP1),0,$MAP1)*$WEIGHT_L1 + if(isnull($MAP2),0,$MAP2)*$WEIGHT_L2";
r.null map=$OUTPUT_LAYER$TMAP setnull=0 --quiet
#r.mapcalc "$OUTPUT_LAYER$TMAP = $MAP1*$WEIGHT_L1 + $MAP2*$WEIGHT_L2";

exit 0;
