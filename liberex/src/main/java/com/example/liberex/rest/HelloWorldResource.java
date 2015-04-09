package com.example.liberex.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldResource {
    @GET
    public String helloGet() {
        return "[GET] Hello from JAX-RS on WebSphere Liberty Profile";
    }

    @POST
    public Response helloPost() {
        return Response.ok("[POST] Hello from JAX-RS on WebSphere Liberty Profile")
                .header("X-Api-Version", "1.0")
                .build();
    }
}
