#
# Docker configuration file for the Basis-Auth project
#

FROM websphere-liberty:webProfile6

COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/
COPY basic-auth.war /opt/ibm/wlp/usr/servers/defaultServer/apps
ENV LICENSE accept

