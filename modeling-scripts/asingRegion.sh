
#!/bin/sh

# This script is Free Software under the GNU GPL (>= 3.0)
#
# Sets  a new resolution for a raster map
#

# Arguments
MAP=$1
SUFFIX=$2

# Variables
VMAP=V_"$MAP"_"$SUFFIX"

# Initialization
export GISRC="/tmp/.grassrc6_$SUFFIX"
export GISBASE="/usr/lib/grass64"
export PATH="$PATH:$GISBASE/bin:$GISBASE/scripts"
export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$GISBASE/lib"

RESULT=$( g.region vect="$VMAP");

exit;
