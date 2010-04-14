#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# exports a resulting map into a png image file format

# Arguments
SUFFIX=$1

# Variables
#RESMAP="RES_$SUFFIX"
RESMAP=R_"$2"_"$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$(r.out.png input=$RESMAP output=$RESMAP.png --quiet);

exit $RESULT;
