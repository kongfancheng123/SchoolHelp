#!/bin/bash

APP_NAME="data-tool"
RETVAL="0"



function start() {
    pid=$(ps -ef | grep -v 'grep' | egrep $APP_NAME| awk '{printf $2 " "}')
    if [ "$pid" != "" ]; then
        echo "$APP_NAME is running,pid is $pid"
    else
        echo "Starting $APP_NAME..."
        nohup java -Dappliction=$APP_NAME -XX:+UseConcMarkSweepGC -Xloggc:../../logs/gc.log -jar ../../*.jar --spring.config.location=../../conf/application.yaml > /dev/null 2>&1 &
    fi
}

function stop() {
    pid=$(ps -ef | grep -v 'grep' | egrep $APP_NAME| awk '{printf $2 " "}')
        if [ "$pid" != "" ]; then
        echo -n "$APP_NAME ( pid $pid) is running"
        echo
        echo -n $"Shutting down $APP_NAME: "
        pid=$(ps -ef | grep -v 'grep' | egrep $APP_NAME| awk '{printf $2 " "}')
        if [ "$pid" != "" ]; then
            echo "kill $APP_NAME process"
            kill -9 "$pid"
        fi
        else
             echo "$APP_NAME is stopped"
        fi

    status
}

function status()
{
    pid=$(ps -ef | grep -v 'grep' | egrep $APP_NAME| awk '{printf $2 " "}')
    if [ "$pid" != "" ]; then
        echo "$APP_NAME is running,pid is $pid"
    else
        echo "$APP_NAME is stopped"
    fi
}



function usage()
{
   echo "Usage: $0 {start|stop|restart|status}"
   RETVAL="2"
}

# See how we were called.
RETVAL="0"
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    reload)
        RETVAL="3"
        ;;
    status)
        status
        ;;
    *)
      usage
      ;;
esac

exit $RETVAL