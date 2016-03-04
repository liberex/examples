System Properties Servlet
=========================

A simple web-application that displays the system properties.

It also shows a discrepancy between the behavior of getSystemProperties on Linux vs. z/OS JVM (JRE7). On Linux, it correctly displays all the properties but on z/OS only shows a few properties:

    cics.runtime=yes
    com.ibm.mq.adapter=cics
    com.ibm.cics.jvmserver=WLPJVM
    org.beanio.validateOnMarshal=true
    cics.java.runtime=jvm

    
 To build the servlet:
 
    mvn package
    
To run the servlet check the [docker](src/main/docker/README.md) installation.