package org.example.api;

import org.example.aop.UserLoggedIn;
import org.example.service.ApiService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hallo")
public class HalloResource {

    @Inject
    private ApiService apiService;

    @GET
    @UserLoggedIn
    public Response hallo() {
        return Response.ok("Message: " + apiService.getServiceDescription()).build();
    }
}
