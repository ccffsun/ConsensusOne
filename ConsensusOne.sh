#!/bin/bash

PROJECT_PATH='/Users/ccffsun/IdeaProjects/ConsensusOne/'

cd "${PROJECT_PATH}"

javac -classpath lib/commons-lang-2.6.jar:lib/j-text-utils-0.3.3.jar:lib/mysql-connector-java-8.0.19.jar -d out src/**/*.java

PROJECT_OUT="${PROJECT_PATH}/out"

cd "${PROJECT_OUT}"

java -cp ${PROJECT_PATH}/lib/j-text-utils-0.3.3.jar:${PROJECT_PATH}/lib/commons-lang-2.6.jar:${PROJECT_PATH}/lib/mysql-connector-java-8.0.19.jar: Main