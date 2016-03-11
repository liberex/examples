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

### Behavior Under Load

The unit-test class [JPerfTestProductCatalogService](src/test/java/net/liberex/JPerfTestProductCatalogService.java) sends a burst of 10 requests in parallel.

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

Note that when the server slows down and the client retries frequently the server cannot recover even 
when the original issue is resolved.

### Handling Client Timeouts

The unit-test class [JPerfTestTimeouts](src/test/java/net/liberex/JPerfTestTimeouts.java) runs three requests with a timeout after 500ms. The server has a processing time of 5 seconds and maximum 2 threads.


The following are the interleaved logs from client and server:

	C> 10:34:53.730,DEBUG,main,n.l.JPerfTestTimeouts,Starting test connecting to url: http://localhost:9090/slow-service
	C> 10:34:53.938,DEBUG,main,n.l.JPerfTestTimeouts,Sending request for product code: ABC
	S< 10:34:53.974,DEBUG,Default Executor-thread-2,n.l.SlowProductCatalogServiceImpl,Received request for product code: ABC
	C> 10:34:54.469,DEBUG,main,n.l.JPerfTestTimeouts,Exception main received after: 531ms
	C> 10:34:54.675,DEBUG,main,n.l.JPerfTestTimeouts,Sending request for product code: ABC
	S< 10:34:54.692,DEBUG,Default Executor-thread-1,n.l.SlowProductCatalogServiceImpl,Received request for product code: ABC
	C> 10:34:55.182,DEBUG,main,n.l.JPerfTestTimeouts,Exception main received after: 507ms
	C> 10:34:55.386,DEBUG,main,n.l.JPerfTestTimeouts,Sending request for product code: ABC
	C> 10:34:55.891,DEBUG,main,n.l.JPerfTestTimeouts,Exception main received after: 505ms
	S< 10:34:58.979,DEBUG,Default Executor-thread-2,n.l.SlowProductCatalogServiceImpl,Send response after 5005ms
	S< 10:34:59.002,DEBUG,Default Executor-thread-2,n.l.SlowProductCatalogServiceImpl,Received request for product code: ABC
	S< 10:34:59.698,DEBUG,Default Executor-thread-1,n.l.SlowProductCatalogServiceImpl,Send response after 5005ms
	S< 10:35:04.005,DEBUG,Default Executor-thread-2,n.l.SlowProductCatalogServiceImpl,Send response after 5003ms
    
 This is a description of the events:
 
  * 0ms - Client sends the request 1
  * 10ms - Server receives the request 1 starts processing in thread 1
  * 500ms - Client times out, sends FIN packet over TCP. Server keeps processing this request 
  * 700ms - Client sends request 2
  * 710ms - Server receives the request to and starts processing in thread 2
  * 1200ms - Client times out, sends FIN packet over TCP. Server keeps processing requsts 1 and 2
  * 1400ms - Client sends request 3. There are no servers available but the server reads the request and keeps it in a queue
  * 1900ms - Client times out on request 3.
  * 5000ms - Server completes the execution of request 1. The TCP connection is closed, response dropped
  * 57000ms - Server completes the execution of request 2. The TCP/IP connection is closed, response dropped
  * 5710ms - Server receives the request 3 which was in the pending queue
  * 10700ms - Server completes request 3 and drops the response because the TCP/IP connection is closed

The TCP dump for this request is saved in the file [client-timeout.pcap](src/test/data/out/client-timeout.pcap). To read its content:

    tcpdump -r src/test/data/out/client-timeout.pcap

The conclusions are:

 * the client closes the TCP/IP connection on timeout (sends FIN) and opens a new one for the next request (SYN)
 * the server acknowledges that the connection closes (sends ACK) but does not stop the threads or remove the pending requests
 

  
 
 