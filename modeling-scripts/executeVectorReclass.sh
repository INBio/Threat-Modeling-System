#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Reclasificate a vector file acording to a unique column,
# this script is specially to use with Categories columns.
#

# Arguments
MAP=$1
COLUMN=$2
SUFFIX=$3

# Variables
VMAP=V_"$MAP"_"$SUFFIX"
VOMAP="$VMAP"_r

export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$(v.reclass input=$VMAP output=$VOMAP column=$COLUMN --overwrite --quiet);

exit $RESULT;
