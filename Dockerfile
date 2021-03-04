FROM jboss/wildfly

MAINTAINER tborgato@redhat.com

#
USER root
RUN yum -y install net-tools
USER jboss

# DEPLOY WAR
ADD target/*.war /opt/jboss/wildfly/standalone/deployments/

#ADD ./docker-files/standalone-ha.xml /opt/jboss/wildfly/standalone/configuration/
ADD ./docker-files/postgresql-42.2.10.jar /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

# ========================================
# CREATE LAUNCH SCRIPT
# ========================================
RUN /bin/bash -c "echo '#!/bin/bash' > /tmp/jboss.sh"
# IPADDRESS
RUN /bin/bash -c "echo 'IPADDRESS=\$(hostname -i)' >> /tmp/jboss.sh"
RUN /bin/bash -c "echo 'echo \"IPADDRESS=\$IPADDRESS\"' >> /tmp/jboss.sh"
# MODIFY standalone-ha.xml
RUN /bin/bash -c "echo $'/opt/jboss/wildfly/bin/jboss-cli.sh <<EOF \n\
embed-server --std-out=echo --server-config=standalone-ha.xml \n\
/interface=multicast:add(inet-address=\$IPADDRESS) \n\
/socket-binding-group=standard-sockets/socket-binding=jgroups-mping:write-attribute(name=interface, value=multicast) \n\
/socket-binding-group=standard-sockets/socket-binding=jgroups-tcp:write-attribute(name=interface, value=multicast) \n\
/socket-binding-group=standard-sockets/socket-binding=jgroups-udp:write-attribute(name=interface, value=multicast) \n\
/subsystem=undertow/server=default-server/host=bigstalker:add(alias=[bigstalker.com]) \n\
/subsystem=undertow/server=default-server/host=bigstalker/setting=access-log:add(prefix=bigstalker) \n\
/subsystem=undertow/server=default-server/host=bigstalker:write-attribute(name=default-web-module,value=bigstalker.war) \n\
data-source add --name=java:jboss/datasources/BigStalkerDS_Pool --jndi-name=java:jboss/datasources/BigStalkerDS --driver-name=postgresql-42.2.10.jar --connection-url=jdbc:postgresql://35.224.237.145:5432/bigstalker --user-name=postgres --password=XdJe64FmhDy3 \n\
quit \n\
EOF\n'\
>> /tmp/jboss.sh"

# START WILDFLY
# CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-server-config=standalone-ha.xml"]
RUN /bin/bash -c "echo \"/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -server-config=standalone-ha.xml\" >> /tmp/jboss.sh"

RUN /bin/bash -c "chmod +x /tmp/jboss.sh"

CMD "/tmp/jboss.sh"