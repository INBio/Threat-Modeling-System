
#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Convert a vector map into a raster map.
#
#

# Arguments
SUFFIX=$1

# Variables
RESULT="RES_$SUFFIX"

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

RES=$(r.out.png input=$RESULT output=$RESULT.png --quiet);

exit $RES;
