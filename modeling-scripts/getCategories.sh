#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Returns a list of the differents categories of a raster map.
#

# Arguments
MAP=$1
SUFFIX=$2
<<<<<<< HEAD

# Variables
VMAP="V_$(basename $MAP .shp)_$SUFFIX"_r
=======
TYPE=$3

# Variables
VMAP=V_"$MAP"_"$SUFFIX"
RMAP=R_"$MAP"_"$SUFFIX"
>>>>>>> next

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

<<<<<<< HEAD
RESULT=$( v.report map=$VMAP option=area units=k | awk -F'|' '{ printf "%s:%s\r",$1,$2}');
=======
if [ "$TYPE" == "VECT" ];
then
	RESULT=$( v.report map=$VMAP option=area units=k | awk -F'|' '{ printf "%s:%s\r",$1,$2}');
else
	RESULT=$( r.category $RMAP | awk -F'\t' '{ printf "%s:%s\r",$1,$2}');
fi;
>>>>>>> next

echo $RESULT

exit;
