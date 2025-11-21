#!/bin/bash

sleep 1

cd /capco/git/
pwd

echo "**** start building **********"
mvn clean install -DskipTests
JARNAME=$(ls -lrt  ./target/*.jar | tail -n 1 | awk '{print $NF}')

cp $JARNAME /capco/target/${APP}/${JARNAME##}*/}
cp pom.xml /capco/target/${APP}

mkdir -p /capco/target/${APP}/src/main/
cp -r src/main/resources/ /capco/target/${APP}/src/main/
