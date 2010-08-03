#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#

SUFFIX="$1"

# GRASS enviromental configuration
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"
export MAPSET="PERMANENT"

# grass database configuration.
export DBASE="$HOME/Projects/sand_box/grass"

# Threats image map folder.
export THREAT_MAPS_FOLDER="$CATALINA_HOME/webapps/resmaps"

# location name
export LOCATION="LOC_$SUFFIX"

# default location
export DEF_LOCATION="Default"

# rules file name
export RULES_FILE="/tmp/rules-$SUFFIX.rcl"

#export temp directory
export TEMP_DIR=/tmp

# vector map
export VMAP="_V_$SUFFIX"
# reclass vector map
export VRMAP="_VR_$SUFFIX"
# raster map
export RMAP="_R_$SUFFIX"
# reclass raster map
export RRMAP="_RR_$SUFFIX"
# THREAT MAP
export TMAP="_T_$SUFFIX"

