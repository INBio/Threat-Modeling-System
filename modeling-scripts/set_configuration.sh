#! /bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Change the configuration of the GRASS GIS environment

# Arguments
SUFFIX=$2

#variables
SCRIPTS_DIR=`dirname $0`

. $SCRIPTS_DIR/conf_grass_vars.sh $1

echo "("`dirname $0`") Este es el GISRC: $GISRC"

# writes the options to the file.
echo "LOCATION_NAME: LOC_$SUFFIX"	>  $GISRC
echo "MAPSET: $MAPSET"				>> $GISRC 
echo "DIGITIZER: none"				>> $GISRC
echo "GISDBASE: $DBASE"				>> $GISRC
echo "GRASS_GUI: text"				>> $GISRC

exit 0;
