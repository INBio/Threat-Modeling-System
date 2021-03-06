#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: change the name of a vectorial map to a Vectorial reclass
# map
#

# Arguments
LAYER=$1
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

# rename the file
g.rename vect=$LAYER$VMAP,$LAYER$VRMAP;

exit 0;
