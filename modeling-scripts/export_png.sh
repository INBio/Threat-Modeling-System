#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# exports a resulting map into a png image file format

# Arguments
SUFFIX=$1

# Variables
#RESMAP="RES_$SUFFIX"
RESMAP=R_"$2"_"$SUFFIX"_r
DEST="$CATALINA_HOME/webapps/resmaps"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"


RESULT=$(r.out.png input=$RESMAP output="$DEST/$RESMAP.png" --quiet);

cp -r "$DEST/$RESMAP.png" "$DEST/$RESMAP"_org.png
#convert "$DEST/$RESMAP".png -scale 10000% -resize 640x480  "$DEST/$RESMAP".png
convert "$DEST/$RESMAP".png  -resize 640x480  "$DEST/$RESMAP".png

exit $RESULT;
