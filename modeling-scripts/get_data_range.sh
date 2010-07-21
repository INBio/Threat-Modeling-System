#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# return the minimun and the maximun values of a raster map.
# in the format:  "min:max"

# Arguments
LAYER=$1
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

RANGE=$(r.info map=$LAYER$RMAP -r);

echo $RANGE |sed "s/ /=/g" | awk -F= '{ printf "%s:%s",$2,$4}'

exit 0;
