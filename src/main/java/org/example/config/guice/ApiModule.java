package org.example.config.guice;

import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.ServletModule;
import org.example.aop.UserLoggedIn;
import org.example.aop.UserLoggedInInterceptor;
import org.example.api.GuiceHalloService;
import org.example.api.HalloResource;
import org.example.config.GuiceBridgeFeature;
import org.example.service.ApiService;
import org.example.service.ApiServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ApiModule extends ServletModule {

    @Override
    protected void configureServlets() {

        UserLoggedInInterceptor userLoggedIn = new UserLoggedInInterceptor();
        requestInjection(userLoggedIn);

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(UserLoggedIn.class), userLoggedIn);

        bind(ApiService.class).to(ApiServiceImpl.class).in(Singleton.class);
        bind(HalloResource.class);
        bind(GuiceHalloService.class);
        serve("/api/*").with(initApiServletContainer());
    }

    private ServletContainer initApiServletContainer() {
        ResourceConfig config = new ResourceConfig();
        config.register(GuiceBridgeFeature.class);
        config.register(HalloResource.class);
        config.packages("org.example.api", "org.example.service");
        return new ServletContainer(config);
    }
}
