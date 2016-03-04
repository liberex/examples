#
# Docker configuration file for the sysprops project
#

FROM websphere-liberty:webProfile6

COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/
COPY sysprops.war /opt/ibm/wlp/usr/servers/defaultServer/dropins/
ENV LICENSE accept
