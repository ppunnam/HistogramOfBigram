#!/bin/sh

arg1=$1
    
dir=./HistogramOfBigram/build/libs
jar_name=HistogramOfBigram-0.0.1.jar

## Permform some validation on input arguments, one example below
if [ -z "$1" ]; then
        echo "Missing arguments, exiting.."
        echo "Usage : $0 arg1"
        exit 1
fi

java -jar $dir/$jar_name $arg1
