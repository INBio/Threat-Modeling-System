#!/bin/sh

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
SUFFIX=$2

# Variables
VMAP="V_$(basename $MAP .shp)_$SUFFIX"

# Initialization
LOCATION="Default"
MAPSET="PERMANENT"
DBASE="$HOME/Projects/sand_box/grass"

export GISRC="$HOME/.grassrc6"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

echo "LOCATION_NAME: $LOCATION"	>  $HOME/.grassrc6
echo "MAPSET: $MAPSET"			>> $HOME/.grassrc6
echo "DIGITIZER: none"			>> $HOME/.grassrc6
echo "GISDBASE: $DBASE"			>> $HOME/.grassrc6
echo "GRASS_GUI: text"			>> $HOME/.grassrc6

# Import the map.
if [ -d "$DBASE/loc_$SUFFIX" ];
then
	RESULT=$(v.in.ogr dsn=$MAP output=$VMAP --quiet);
else
	RESULT=$(v.in.ogr dsn=$MAP output=$VMAP location=loc_$SUFFIX --quiet);
fi;

exit $RESULT;
