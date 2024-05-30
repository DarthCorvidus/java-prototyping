#!/bin/bash
mvn package && java -cp target/java-prototyping-1.0-SNAPSHOT.jar com.corvidus.prototyping.$1
