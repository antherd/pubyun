pid=`ps -ef|grep "pubyun"|grep -v grep|awk '{print $2}'`
kill -9 $pid

sleep 3

nohup java -Dfile.encoding=utf-8 -jar pubyun.jar > logs.log &

tail -200f logs.log
