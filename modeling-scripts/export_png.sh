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
r.out.png input=$FINAL_MAP output="$THREAT_MAPS_FOLDER/$FINAL_MAP.png" --quiet;

# resize the image
#cp -r "$THREAT_MAPS_FOLDER/$RESMAP.png" "$THREAT_MAPS_FOLDER/$FINAL_MAP"_org.png
convert "$THREAT_MAPS_FOLDER/$FINAL_MAP.png" -transparent white -resize 640x480 "$THREAT_MAPS_FOLDER/$FINAL_MAP.png"

exit 0;
