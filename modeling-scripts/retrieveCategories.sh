#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Returns a list of the differents categories of a raster map.
#

# Arguments
MAP=$1
SUFFIX=$2
TYPE=$3

# Variables
VMAP=V_"$MAP"_"$SUFFIX"
RMAP=R_"$MAP"_"$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

if [ "$TYPE" == "VECT" ];
then
	v.category map=$VMAP fs=:;
else
	r.category map=$RMAP fs=: 
fi;
