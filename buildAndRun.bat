@echo off
call mvn clean package
call docker build -t com/bigstalker .
call docker rm -f bigstalker
call docker run -d -p 9080:9080 -p 9443:9443 --name bigstalker com/bigstalker