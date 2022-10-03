#!/bin/bash

# (Linux) Use when Java 17 is not default, replace with your Java 17 directory
export JAVA_HOME="/opt/java/amazon_jdk/amazon-corretto-17.0.1.12.1-linux-x64"
echo JAVA_HOME=\"$JAVA_HOME\"

#./gradlew run --args='https://blogs.oracle.com/javamagazine/rss'
./gradlew clean jar
java -jar build/libs/stringer-0.2.0.jar https://blogs.oracle.com/javamagazine/rss
