#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# change the color scale of the map
#

# Arguments
MAP=$1
SUFFIX=$2

# Variables
RMAP=R_"$MAP"_"$SUFFIX"_r

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

## Posible color scales
# - ------ +
#	bcyr
#	bgyr
#	byr
#	byg
#	gyr
#	ryb
#	ryg
#
## Leyend
#	b:	blue
#	c:	cyan
#	g:	green
#	y:	yellow
#	r:	red

RESULT=$( r.colors map="$RMAP" color="gyr");

echo $RESULT

exit;
