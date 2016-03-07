Web Services with Authentication
================================

This is an example of a conversational web-service. The client logs in, executes an operation (authenticated) and then logs out.

Build the application:

mvn package

Package as a docker container. Details [here](src/main/docker/README.md)

###Test 1 - Successful authentication

Login:

    curl -v --cookie-jar ./cookies.txt -u DEVAB:123 -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/LoginRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService
    
Session Id Returned as cookie:

	> POST /ws-auth/ProductCatalogService HTTP/1.1
	> Host: 192.168.99.100:84
	> Authorization: Basic REVWQUI6MTIz
	> User-Agent: curl/7.43.0
	> Accept: */*
	> Content-Type:text/xml;charset=UTF-8
	> Content-Length: 156

	< HTTP/1.1 200 OK
	< X-Powered-By: Servlet/3.0
	< Content-Type: text/xml; charset=UTF-8
	< Content-Length: 170
	< Set-Cookie: SessionId=nxjQHo7zypyHIA2D01pHqra....80+/; Path=/; HttpOnly
	< Date: Mon, 07 Mar 2016 04:05:23 GMT
	< Expires: Thu, 01 Dec 1994 16:00:00 GMT
	< Cache-Control: no-cache="set-cookie, set-cookie2"
	<
	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	    <soap:Body><LoginResponse xmlns="urn:net.liberex:pc:v1"></LoginResponse></soap:Body>
    </soap:Envelope>


Retrieve product details:

    curl -v -b ./cookies.txt -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/GetProductDetailsRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService
    
Session Id is passed as cookie and used in authentication:

	> POST /ws-auth/ProductCatalogService HTTP/1.1
	> Host: 192.168.99.100:84
	> User-Agent: curl/7.43.0
	> Accept: */*
	> Cookie: SessionId=nxjQHo7zypy........80+/
	> Content-Type:text/xml;charset=UTF-8
	> Content-Length: 243
    ....
    
	< HTTP/1.1 200 OK
	< X-Powered-By: Servlet/3.0
	< Content-Type: text/xml; charset=UTF-8
	< Content-Length: 261
	< Date: Mon, 07 Mar 2016 04:08:50 GMT
	<
	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	    <soap:Body><GetProductDetailsResponse xmlns="urn:net.liberex:pc:v1">
	        <Product code="ABC" description="SuperHighSpeedInternet"></Product>
	        </GetProductDetailsResponse></soap:Body>
	</soap:Envelope>

Logout:

    curl -v -b ./cookies.txt -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/LogoutRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService


###Test 2 - Failed authentication, return error

    curl -v --cookie-jar ./cookies.txt -u DEVAB:badpass -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/LoginRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService

Subsequent calls also fail:

    curl -v -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/GetProductDetailsRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService
    
    