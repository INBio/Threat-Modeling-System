#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Sets  a new resolution for a raster map
#

# Arguments
RESOLUTION=$1
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

# set the resolution
g.region res=$RESOLUTION;

exit;
