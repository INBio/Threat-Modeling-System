#! /bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Import a shapefile into the grass database. 
#

# Arguments
LAYER_URI=$1
LAYER_NAME=$2
SUFFIX=$3

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

#import the shapefile
v.in.ogr -o dsn=$LAYER_URI$MAP output=$LAYER_NAME$VMAP --quiet;

exit 0;
