Slow Service with Pending Requests
==================================

The project implements a slow version of the ProductCatalogService interface. The GetProductDetails operation waits 5s before
returning the response. We are using this class to understand the server's behavior when the request arrival rate is higher than
the service rate.

### Build the application

Use maven:

    mvn package

### Deploy in the container

Instructions for packaging the application in a Docker container are in the [README](src/main/docker/README.md) file.

### Behavior under load

The unit-test class JPerfTestProductCatalogService can send multiple requests in parallel. The checked in configuration
sends a burst of 10 requests at the same time.

The server is configured with maxTreads = 2. See [server.xml](src/main/docker/server.xml).

The following log entries show that the server queues the requests and allocates threads when they become available:

	12:06:42.037 [Thread-1] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-1, response time: 5214
	12:06:42.037 [Thread-6] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-6, response time: 5214
	12:06:47.046 [Thread-5] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-5, response time: 10227
	12:06:47.046 [Thread-4] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-4, response time: 10227
	12:06:52.065 [Thread-9] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-9, response time: 15246
	12:06:52.065 [Thread-10] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-10, response time: 15246
	12:06:57.090 [Thread-8] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-8, response time: 20271
	12:06:57.090 [Thread-7] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-7, response time: 20271
	12:07:02.107 [Thread-2] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-2, response time: 25288
	12:07:02.107 [Thread-3] DEBUG n.l.JIntTestProductCatalogService - Thread: Thread-3, response time: 25288

The side-effect of this behavior is that when the server slows down and the client retries frequently the server
cannot recover even when the original issue is resolved.

