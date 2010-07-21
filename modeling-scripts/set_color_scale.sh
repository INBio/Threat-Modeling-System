#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: change the color scale of the map
#

# Arguments
LAYER=$1
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

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

r.colors map="$LAYER$TMAP" color="gyr";

exit 0;
