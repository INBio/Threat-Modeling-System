#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Return a list of Categories.

# Arguments
LAYER=$1
TYPE=$2
SUFFIX=$3

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX


if [ "$TYPE" == "VECT" ];
then
	v.category map=$LAYER$VMAP fs=:;
else
	r.category map=$LAYER$RMAP fs=: 
fi;

exit 0;
