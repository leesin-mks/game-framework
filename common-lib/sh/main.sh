#!/bin/sh
#main.sh
#To start or stop gameserver.

#base dir of the application
APP_BASE=/game/app
GAME_SERVER=$APP_BASE/gameserver
GAME_SERVER1=$APP_BASE/gameserver1

FIGHT_SERVER=$APP_BASE/fightserver
FIGHT_SERVER1=$APP_BASE/fightserver1

LOGIN_SERVER=/usr/local/tomcat1/bin/
LOGIN_SERVER1=/usr/local/tomcat2/bin/

echo $APP_BASE


function startgame()
{
	cd $FIGHT_SERVER
    sh fightserver.sh start
	
	cd $FIGHT_SERVER1
    sh fightserver.sh start
    
	cd $GAME_SERVER
	sh gameserver.sh start
	
	cd $GAME_SERVER1
	sh gameserver.sh start
	
    cd $LOGIN_SERVER
	sh startup.sh
	
	cd $LOGIN_SERVER1
	sh startup.sh
}

function stopgame()
{
	cd $FIGHT_SERVER
    sh fightserver.sh stop
	
	cd $FIGHT_SERVER1
    sh fightserver.sh stop
    
	cd $GAME_SERVER
	sh gameserver.sh stop
	
	cd $GAME_SERVER1
	sh gameserver.sh stop
	
    cd $LOGIN_SERVER
	sh shutdown.sh
	
	cd $LOGIN_SERVER1
	sh shutdown.sh
}

case "$1" in
    start)
    
    startgame

    ;;
    stop)
	
     stopgame
     
    ;;
    restart)
	
	stopgame
	
	sleep 20
	
	startgame
	
	;;
    *)
        echo "Usage: $0 start|stop|restart"
    ;;
esac
exit 0
