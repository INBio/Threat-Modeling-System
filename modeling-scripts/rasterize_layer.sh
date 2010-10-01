#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Convert a vector map into a raster map.
#

# Arguments
LAYER=$1
COLUMN=$2
SUFFIX=$3

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

# Import the map.
if [ "" == "$COLUMN" ] || [ "cat" == "$COLUMN" ];
then
	v.to.rast input=$LAYER$VRMAP output=$LAYER$RMAP use=val value=1 --overwrite --quiet;
else
	v.to.rast input=$LAYER$VRMAP output=$LAYER$RMAP use=attr column=cat labelcolumn="$COLUMN" --overwrite --quiet;
fi

exit 0;
