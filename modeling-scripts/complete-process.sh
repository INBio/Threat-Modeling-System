#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
# Import 2 shapefiles, converts them into raster maps and 
# operates over them to generate a map of threats.
#
# It uses the following formula:
#		result = map1 * (map1_weight/100) + map2 * (map2_weight/100).
#		para map1_weight + map2_weight = 100%

# Arguments
MAP1=$1
WEIGHT_MAP1=$2

MAP2=$3
WEIGHT_MAP2=$4

RESOLUTION=$5

# --- Internal variables ---
TIME=$(date +%s)

# --- Vector output names. ---
VMAP1="V_$(basename $MAP1 .shp)_$TIME"
VMAP2="V_$(basename $MAP2 .shp)_$TIME"

# --- Raster output names. ---
RMAP1="R_$(basename $MAP1 .shp)_$TIME"
RMAP2="R_$(basename $MAP2 .shp)_$TIME"

# --- Resulting map name. ---
RESULT="RES_$TIME"

#initialization
LOCATION="Default"
MAPSET="PERMANENT"
DBASE="$HOME/Projects/sand_box/grass"

echo "LOCATION_NAME: $LOCATION"
echo "MAPSET: $MAPSET"		
echo "DIGITIZER: none"	
echo "GISDBASE: $DBASE"
echo "GRASS_GUI: text"

echo "LOCATION_NAME: $LOCATION"	>  $HOME/.grassrc6
echo "MAPSET: $MAPSET"			>> $HOME/.grassrc6
echo "DIGITIZER: none"			>> $HOME/.grassrc6
echo "GISDBASE: $DBASE"			>> $HOME/.grassrc6
echo "GRASS_GUI: text"			>> $HOME/.grassrc6

export GISBASE="/usr/lib/grass64"
export GISRC="$HOME/.grassrc6"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"


# Import the maps.
echo "#-> Importing $MAP1"
v.in.ogr dsn=$MAP1 output=$VMAP1 location=loc_$TIME;

echo "LOCATION_NAME: loc_$TIME"	>  $HOME/.grassrc6
echo "MAPSET: $MAPSET"			>> $HOME/.grassrc6
echo "DIGITIZER: none"			>> $HOME/.grassrc6
echo "GISDBASE: $DBASE"			>> $HOME/.grassrc6
echo "GRASS_GUI: text"			>> $HOME/.grassrc6

echo "#-> Importing $MAP2"
v.in.ogr dsn=$MAP2 output=$VMAP2 

# Convert from vector to raster.
echo "#-> Converting into raster format $MAP1"
v.to.rast input=$VMAP1 output=$RMAP1 use=cat
echo "#-> Converting into raster format $MAP2"
v.to.rast input=$VMAP2 output=$RMAP2 use=cat

# Reclasification of the raster information.
# Changing resolution.
echo "#-> Changing resolution"
# Mathematical operation.
echo "#-> Aritmetic..."
r.mapcalc "$RESULT = $RMAP1*$WEIGHT_MAP1 + $RMAP2*$WEIGHT_MAP2"

#exporting the work
echo "#-> Exporting to png"
r.to.png input=$RESULT output=$RESULT.png

echo "#-> Exporting to vectorial "
r.to.vect input=$RESULT output=$RESULT feature=area

echo "#-> Exporting to shapefile "
v.out.ogr input=$RESULT type=area dsn=$RESULT layer=1 format=ESRI_Shapefile 

exit;
