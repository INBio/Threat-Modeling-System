#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Set the new categories to a layer.
#

# Arguments
LAYER=$1
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

# reclass the specified layer
r.reclass input=$LAYER$RMAP output=$LAYER$RRMAP rules=$RULES_FILE --overwrite;

exit 0;
