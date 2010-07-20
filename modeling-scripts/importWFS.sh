#! /bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Import a shapefile into the grass database. This script appends the
# SUFFIX to basename of the Path file.
#
# It creates a new location location with the name: loc_$SUFFIX if it
# doesn't exist.
#

# Arguments
MAP=$1
OUTPUT_MAP="$2"
SUFFIX="$3"

# Variables
VMAP=V_"$OUTPUT_MAP"_"$SUFFIX"
DBASE="$HOME/Projects/sand_box/grass"

# Environment initialization
export GISRC="/tmp/.grassrc6_$SUFFIX" #temporal GRASS RC file
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

# Import the map.
RESULT=$(v.in.wfs wfs=$MAP output=$VMAP --quiet);

exit $RESULT;
