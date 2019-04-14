#!/bin/bash

for i in $*;
do
	params=" $params $d/$i"
done

java -cp target/parkingSolution-0.0.1-SNAPSHOT.jar com.gojek.parkingSolution.App $params
