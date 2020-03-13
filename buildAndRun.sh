#!/bin/sh
mvn clean package && docker build -t com/bigstalker .
docker rm -f bigstalker || true && docker run -d -p 9080:9080 -p 9443:9443 --name bigstalker com/bigstalker