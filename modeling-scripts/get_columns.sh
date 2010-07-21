#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Return a list with the name of the available columns and its data type
#

# Arguments
MAP=$1
SUFFIX=$2

# Variables
VMAP=V_"$MAP"_"$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$( v.info -c map=$VMAP | awk -F'|' '{ printf "%s:%s\r",$2,$1}');

echo $RESULT

exit;
