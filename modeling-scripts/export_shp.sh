#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Exports a resulting map into a ESRI Shapefile file format
#

# Arguments
SUFFIX=$1
FINAL_MAP="$2_$TMAP"

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

# convert the resulting map into vector format.
r.to.vect input=$FINAL_MAP output=$FINAL_MAP feature=area --quiet;

# export the vectorial resulting map into a ESRI Shapefile
v.out.ogr -c input=$FINAL_MAP type=area dsn=$FINAL_MAP layer=1 format=ESRI_Shapefile  --quiet;

exit 0;
