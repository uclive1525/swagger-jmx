#!/bin/sh

export JAVA_HOME=/media/zgq/data/software/jdk/java-1.8.0-openjdk-amd64
#export JAVA_HOME=/opt/jdk-11
export JRE_HOME=${JAVA_HOME}/jre


#if not exist "%JAVA_HOME%\bin\java" echo Please export the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
export "JAVA=%JAVA_HOME%\bin\java"


export BASE_DIR=%~dp0

export BASE_DIR="%BASE_DIR:~0,-5%"

# the swagger address, which can be online or local
export SWAGGER_LOCATION=http://47.97.154.153:8201/hr/v2/api-docs

# File directory for JMeter script output
export JMX_FILE_DIR=/media/zgq/data/code/git.youlu.com/dev-test-team-project/api-test/swaggerTest/swagger2jmx-plugin/src/main/resources/jmx/

export SERVER=swagger2jmx-plugin

# export exec options
export "EXEC_OPTS=-Dfile.encoding=utf-8"
export "EXEC_OPTS=%EXEC_OPTS% -jar %BASE_DIR%\%SERVER%.jar"
export "EXEC_OPTS=%EXEC_OPTS% --i=%SWAGGER_LOCATION% --o=%JMX_FILE_DIR%"

export COMMAND="%JAVA%" %EXEC_OPTS%

# start generate command
%COMMAND%

pause