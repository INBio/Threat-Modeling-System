#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Exports a resulting map into a ESRI Shapefile file format
#

# Arguments
SUFFIX=$1

# Variables
RESMAP="RES_$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

# convert the resulting map into vector format.
RESULT=$(r.to.vect input=$RESMAP output=$RESMAP feature=area --quiet);
# export the vectorial resulting map into a ESRI Shapefile
RESULT=$(v.out.ogr -c input=$RESMAP type=area dsn=$RESMAP layer=1 format=ESRI_Shapefile  --quiet);

exit $RESULT;
