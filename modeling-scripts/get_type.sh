#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description: Return the type of the shapefile acording to the
# topological information of the v.info command
#

# Arguments
LAYER=$1
SUFFIX=$2

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX

v.info -t map=$LAYER$VMAP | \
	egrep "points|lines|areas" | \
	awk '{ 
			split($1, array, "=");
			if(array[2] != 0)
				print array[1]; 
		 }';

exit 0;
