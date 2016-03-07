Docker Configuration for ws-auth Example
===============================================

### Build image containing the WAR file

Build the image

    cp target/ws-auth.war src/main/docker
    docker build -t=liberex:ws-auth src/main/docker

Run the container:

    docker run -d -p 84:9080 --name ws-auth liberex:ws-auth

Test 1 - success

    curl -v http://192.168.99.100:84/ws-auth/index.html
    curl -v http://192.168.99.100:84/ws-auth/ProductCatalogService?wsdl

Stop the container:

    docker stop ws-auth

Remove the container

    docker rm -f ws-auth
    docker rmi -f liberex:ws-auth

