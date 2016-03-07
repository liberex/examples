Web Services with Authentication
================================

### Build the application

mvn package

### Deploy in the container

Package as a docker container. Details [here](src/main/docker/README.md)

In the rest of this document 192.168.99.100:84 is the ip/port for the docker container.

###Test 1 - Successful authentication

Login:

    curl -v --cookie-jar ./cookies.txt -u DEVAB:123 -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/LoginRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService
    
Session Id returned as cookie:

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
    
Session Id is passed in a cookie and used in authentication:

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


###Test 2 - Invalid Password

Failed authentication, return error

    curl -v --cookie-jar ./cookies.txt -u DEVAB:badpass -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/LoginRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService

Response contains the error information - this is not a container 403 error:

	> POST /ws-auth/ProductCatalogService HTTP/1.1
	> Host: 192.168.99.100:84
	> Authorization: Basic REVWQUI6YmFkcGFzcw==
	> User-Agent: curl/7.43.0
	> Accept: */*
	> Content-Type:text/xml;charset=UTF-8
	> Content-Length: 156


	< HTTP/1.1 200 OK
	< X-Powered-By: Servlet/3.0
	< Content-Type: text/xml; charset=UTF-8
	< Content-Length: 263
	< Date: Mon, 07 Mar 2016 13:17:33 GMT
	<
	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	     <soap:Body><LoginResponse xmlns="urn:net.liberex:pc:v1">
	         <Error errorCode="1001" errorMessage="User failed to be authenticated. Details ... "></Error></LoginResponse>
	         </soap:Body>
    </soap:Envelope



Subsequent call to GetProductDetails returns HTTP error code 200 and a business error in the body:

    curl -v -X POST --header "Content-Type:text/xml;charset=UTF-8" --data @src/test/data/GetProductDetailsRequest.xml http://192.168.99.100:84/ws-auth/ProductCatalogService
    
    
	> POST /ws-auth/ProductCatalogService HTTP/1.1
	> Host: 192.168.99.100:84
	> User-Agent: curl/7.43.0
	> Accept: */*
	> Content-Type:text/xml;charset=UTF-8
	> Content-Length: 243
	>

	< HTTP/1.1 200 OK
	< X-Powered-By: Servlet/3.0
	< Content-Type: text/xml; charset=UTF-8
	< Content-Length: 267
	< Date: Mon, 07 Mar 2016 13:19:40 GMT
	<
	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	    <soap:Body>
	         <GetProductDetailsResponse xmlns="urn:net.liberex:pc:v1">
	              <Error errorCode="1001" errorMessage="Request not authenticated"></Error></GetProductDetailsResponse>
	         </soap:Body>
	</soap:Envelope>

If the web-resource has a auth-constraint:

        <!--  Authentication by the container  -->
        <auth-constraint>
            <role-name>allAuthenticatedUsers</role-name>
        </auth-constraint>

Then the login request with a bad password the container will return HTTP return code 401 without a body.

	> POST /ws-auth/ProductCatalogService HTTP/1.1
	> Host: localhost:9090
	> Authorization: Basic REVWQUI6YmFkcGFzcw==
	> User-Agent: curl/7.43.0
	> Accept: */*
	> Content-Type:text/xml;charset=UTF-8
	> Content-Length: 156
	>

	< HTTP/1.1 401 Unauthorized
	< X-Powered-By: Servlet/3.0
	* Authentication problem. Ignoring this.
	< WWW-Authenticate: Basic realm="defaultRealm"
	< Content-Language: en-US
	< Content-Length: 0
	< Date: Mon, 07 Mar 2016 15:38:05 GMT
	<