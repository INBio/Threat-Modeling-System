#!/bin/sh
#
# This script is Free Software under the GNU GPL (>= 3.0)
#
# Description:

# Arguments
SUFFIX=$1

# configure environment
SCRIPTS_DIR=`dirname $0`
. $SCRIPTS_DIR/set_grass_variables.sh $SUFFIX


#create a new Location base in the default location.
cp -rf $DBASE/$DEF_LOCATION $DBASE/$LOCATION;

exit 0;
