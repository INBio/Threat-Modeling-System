#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Sets the new categories to a map.
#

# Arguments
MAP=$1
SUFFIX=$2

# Variables
RMAP=R_"$MAP"_"$SUFFIX"
ROMAP=R_"$MAP"_"$SUFFIX"_r 

RULES_FILE="/tmp/rules-$SUFFIX.rcl"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$( r.reclass input=$RMAP output=$ROMAP rules=$RULES_FILE --overwrite);

echo $RESULT

exit;
