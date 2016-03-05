Basic Auth Example
===================

Build the application:

mvn package

Package as a docker container. Details [here](src/main/docker/README.md)

Test 1 - success

    curl -v -u DEVAB:123 http://192.168.99.100:83/basic-auth

See in the output the SessionId:

	> GET /basic-auth/hello HTTP/1.1
	> Host: 192.168.99.100:83
	> Authorization: Basic REVWQUI6MTIz
	> User-Agent: curl/7.43.0
	> Accept: */*
	
	< HTTP/1.1 200 OK
	< X-Powered-By: Servlet/3.0
	< Content-Type: text/html;charset=ISO-8859-1
	< Content-Language: en-US
	< Set-Cookie: SessionId=kPY1c3Z63kW6Wh3PNUknr0A.....e5hwRjUhn; Path=/; HttpOnly
	< Transfer-Encoding: chunked
	< Date: Sat, 05 Mar 2016 03:28:55 GMT
	< Expires: Thu, 01 Dec 1994 16:00:00 GMT
	< Cache-Control: no-cache="set-cookie, set-cookie2"
	<
	<h1>Hello DEVAB</h1>

Test 2 - authentication failure

    curl -v -u DEVAB:baspass http://192.168.99.100:83/basic-auth/hello

Output:

	> GET /basic-auth/hello HTTP/1.1
	> Host: 192.168.99.100:83
	> Authorization: Basic REVWQUI6YmFzcGFzcw==
	> User-Agent: curl/7.43.0
	> Accept: */*
	
	< HTTP/1.1 401 Unauthorized
	< X-Powered-By: Servlet/3.0
	< WWW-Authenticate: Basic realm="defaultRealm"
	< Content-Language: en-US
	< Content-Length: 0
	< Date: Sat, 05 Mar 2016 03:29:48 GMT
    

