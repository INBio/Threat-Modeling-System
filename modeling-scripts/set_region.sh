#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Set  a new resolution for a raster map
#

# Arguments
LAYER=$1
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

# set the new region
g.region vect="$LAYER$VMAP";

exit 0;
