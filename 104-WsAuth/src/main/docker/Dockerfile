#
# Docker configuration file for the Basis-Auth project
#

FROM websphere-liberty:8.5.5.8-webProfile6@sha256:9d72e31147528c68985e144b6f41a2155a6408dead487021f8fc72dd12af3698

COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/
COPY ws-auth.war /opt/ibm/wlp/usr/servers/defaultServer/apps

RUN installUtility install --acceptLicense defaultServer
ENV LICENSE accept
