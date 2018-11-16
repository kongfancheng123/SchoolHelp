chcp 65001
@echo off

setlocal
set CLASSPATH=%CLASSPATH%;
set PATH=%PATH%
set JAVA_HOME=%JAVA_HOME%
set APP_NAME=serotonin-1.0-SNAPSHOT
title %APP_NAME%

set RETVAL="0"
set command=%1
if "%command%" == "start" goto start
if "%command%" == "stop" goto stop
if "%command%" == "status" goto status

:start
echo check if %APP_NAME% is running
tasklist /fi "windowtitle equ %APP_NAME%" >nul 2>nul
if %errorlevel% == 0 ( echo 服务已经启动
) else (
echo begin to start service...
echo %java_opts%
 java -XX:+UseConcMarkSweepGC -Xloggc:../../logs/gc.log -Dspring.config.location=../../conf/application.yml -jar ../../%APP_NAME%.jar
 title %APP_NAME%
)
pause
goto:eof

:stop
set status=1
(tasklist /fi "windowtitle eq %APP_NAME%"||set status=0)>nul 2>nul 1>nul
echo %status%
if %status% == 0 ( echo "%APP_NAME% is not running"
) else (
   taskkill /f /t /fi "windowtitle eq %APP_NAME%"
   echo "%APP_NAME% is stopped"
)
pause
goto:eof

:status
tasklist | findstr %APP_NAME%>nul
if %errorlevel% == 0 ( echo " %APP_NAME% is running"
) else (
echo  %APP_NAME% is stopped
)
pause
goto:eof