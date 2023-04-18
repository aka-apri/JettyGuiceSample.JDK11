package org.example.api;

import org.example.service.AopTool;
import org.example.service.ApiService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hallo")
public class HalloRestResource {

    private final ApiService apiService;
    private final AopTool aop;

    @Inject
    public HalloRestResource(
            ApiService apiService,
            AopTool aop
    ){
        this.apiService = apiService;
        this.aop = aop;
    }

    @GET
    public Response hallo() {

        return aop.userLoggedIn(() ->
                aop.transactional(() ->
                        Response.ok("Message: " + apiService.getServiceDescription()).build()
                )
        );
    }
}
