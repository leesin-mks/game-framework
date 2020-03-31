#!/bin/sh
#main.sh
#To start or stop gameserver.

#base dir of the application
APP_BASE=/maquan/test/app/gameserver
GAME_SERVER=$APP_BASE
LOGIN_SERVER=/maquan/test/web/tomcat/bin/

echo $APP_BASE


case "$1" in
    start)
    
	cd $GAME_SERVER
	sh gameserver.sh start

    cd $LOGIN_SERVER
	sh startup.sh
       # /usr/local/nginx/sbin/nginx -c /root/dandantang/Web/nginx.conf.default
    ;;
    stop)
	cd $GAME_SERVER
	sh gameserver.sh stop
	
	cd $LOGIN_SERVER
	sh shutdown.sh
        # kill -INT `cat /root/dandantang/Web/nginx.pid`
    ;;
    restart)
	cd $GAME_SERVER
	sh gameserver.sh stop

	cd $LOGIN_SERVER
	sh shutdown.sh
	sleep 5
	
	cd $GAME_SERVER
	sh gameserver.sh start

	cd $LOGIN_SERVER
	sh startup.sh
	;;
    *)
        echo "Usage: $0 start|stop|restart"
    ;;
esac
exit 0
