Docker Configuration for Basic-Auth Example
===============================================

### Build image containing the WAR file

Build the image

    cp ../../../target/basic-auth.war .
    docker build -t=liberex:basic-auth .

Run the container:

    docker run -d -p 83:9080 --name basic-auth liberex:basic-auth

Test 1 - success

    curl -v -u DEVAB:123 http://192.168.99.100:83/basic-auth

Stop the container:

    docker stop basic-auth

Remove the container

    docker rm -f basic-auth

