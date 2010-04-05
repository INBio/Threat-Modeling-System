#! /bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Change the configuration of the executed scripts

# Arguments
LOCATION=$1
SUFFIX=$2

# Variables
DBASE="$HOME/Projects/sand_box/grass"
GISRC="/tmp/.grassrc6_$SUFFIX"
MAPSET="PERMANENT"

# writes the options to the file.
echo "LOCATION_NAME: $LOCATION"	>  $GISRC
echo "MAPSET: $MAPSET"			>> $GISRC 
echo "DIGITIZER: none"			>> $GISRC
echo "GISDBASE: $DBASE"			>> $GISRC
echo "GRASS_GUI: text"			>> $GISRC

exit 0;
