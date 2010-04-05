
#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Convert a vector map into a raster map.
#
#

# Arguments
LOCATION=$1
SUFFIX=$2

MAPSET="PERMANENT"
DBASE="$HOME/Projects/sand_box/grass"
GISRC="$HOME/.grassrc6_$SUFFIX"

echo "LOCATION_NAME: $LOCATION"	>  $GISRC
echo "MAPSET: $MAPSET"			>> $GISRC 
echo "DIGITIZER: none"			>> $GISRC
echo "GISDBASE: $DBASE"			>> $GISRC
echo "GRASS_GUI: text"			>> $GISRC

exit 0;
