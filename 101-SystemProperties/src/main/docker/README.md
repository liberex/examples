Launching the application in Docker
===================================

###Option A - WAR file as shared volume

Launch the container

    cp ../../../target/sysprops.war .
    docker run -d -p 81:9080 -v $PWD/sysprops.war:/opt/ibm/wlp/usr/servers/defaultServer/dropins/sysprops.war --name sysprops1 websphere-liberty:webProfile6


... wait one minute for the container to start. Then retrieve the content

    curl http://192.168.99.100:81/sysprops


Stop the container

    docker stop sysprops1

Remove the container

    docker rm -f sysprops1


###Option B - Build image containing the WAR file

Build the image

    cp ../../../target/sysprops.war .
    docker build -t=liberex/sysprops .

Run the container:

    docker run -d -p 82:9080 --name sysprops2 liberex/sysprops

Check the content:

    curl http://192.168.99.100:82/sysprops

Stop the container:

    docker stop sysprops2

Remove the container

    docker rm -f sysprops2

