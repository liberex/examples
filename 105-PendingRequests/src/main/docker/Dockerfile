#
# Docker configuration file for the Basis-Auth project
#

FROM websphere-liberty:8.5.5.8-webProfile6

COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/
COPY slow-service.war /opt/ibm/wlp/usr/servers/defaultServer/apps

RUN installUtility install --acceptLicense defaultServer
ENV LICENSE accept
