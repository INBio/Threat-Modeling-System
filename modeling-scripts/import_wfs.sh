#! /bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Import a layer using WFS
#

# Arguments
LAYER_URI=$1
LAYER_NAME=$2
SUFFIX=$3

# Variables
VMAP=V_"$OUTPUT_MAP"_"$SUFFIX"

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

# Import the map by wfs
v.in.wfs wfs=$LAYER_URI output=$LAYER_NAME$VMAP --quiet;

exit 0;
