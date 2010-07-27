#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: export a Treats map into a png image file format

# Arguments
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

FINAL_MAP=$1$TMAP

#export the image
r.out.tiff input=$FINAL_MAP compression=none output="$THREAT_MAPS_FOLDER/$FINAL_MAP.tiff" --quiet;

exit 0;
