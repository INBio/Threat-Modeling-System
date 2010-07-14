#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# change the color scale of the map
#

# Arguments
LOCATION=$1

# Variables
DBASE="$HOME/Projects/sand_box/grass"

cp -rf $DBASE/Default $DBASE/$LOCATION

exit 0;
