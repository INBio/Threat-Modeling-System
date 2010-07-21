#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#

SUFFIX=$1

#grass database configuration.
export DBASE="$HOME/Projects/sand_box/grass"

export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

exit 0;
