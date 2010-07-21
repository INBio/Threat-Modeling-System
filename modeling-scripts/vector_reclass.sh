#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Reclasificate a vector file acording to a unique column,
# this script is specially to use with Categories columns.
#

# Arguments
LAYER=$1
COLUMN=$2
SUFFIX=$3

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX


if [ "" == "$COLUMN" ];
then
	v.reclass input=$LAYER$VMAP output=$LAYER$VRMAP column="cat" --overwrite --quiet;
else
	v.reclass input=$LAYER$VMAP output=$LAYER$VRMAP column=$COLUMN --overwrite --quiet;
fi

exit 0;
