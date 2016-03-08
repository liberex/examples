Docker Configuration for Slow Service
===============================================

### Build image containing the WAR file

Build the image:

    cp target/slow-service.war src/main/docker
    docker build -t=liberex:slow-service src/main/docker

Run the container:

    docker run -d -p 85:9080 --name slow-service liberex:slow-service

Test Deployment:

    curl -v http://192.168.99.100:85/slow-service/index.html
    curl -v http://192.168.99.100:85/slow-service/ProductCatalogService?wsdl
    
    mvn -Djtest=**/JPerfTest* -Dsvc.url=http://192.168.99.100:85/slow-service test

Stop the container:

    docker stop slow-service

Remove the container:

    docker rm -f slow-service
    docker rmi -f liberex:slow-service

