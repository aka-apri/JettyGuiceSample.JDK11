package org.example.api;


import org.example.aop.UserLoggedIn;
import org.example.service.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/api2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuiceHalloService {

    @Inject
    private ApiService apiService;

    @GET
    @Path("/hallo")
    @UserLoggedIn
    public Response hallo() {
        return Response.ok("Guice Message: " + apiService.getServiceDescription()).build();
    }
}
