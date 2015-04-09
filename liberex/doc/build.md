### Generating the Java classes

/apps/liberty/wlp/bin/jaxws/wsimport -d target/generated-sources -b src/main/resources/bindings/liberex.jaxb.xml -target 2.2 -keep -Xnocompile -p com.example.liberex.xdo src/main/resources/schemas/WS_GetSystemStatus.wsdl


### Installing JCICS libraries
mvn install:install-file -DgroupId=com.ibm.cics -DartifactId=server -Dversion=5.2 -Dpackaging=jar -Dfile=com.ibm.cics.server.jar
