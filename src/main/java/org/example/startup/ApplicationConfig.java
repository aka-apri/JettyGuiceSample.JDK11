package org.example.startup;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.api.HalloResource;
import org.example.config.GuiceBridgeFeature;
import org.example.config.guice.ApiModule;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

    @Inject
    public ApplicationConfig(ServiceLocator serviceLocator) {
        ResourceConfig config = new ResourceConfig();
        config.register(GuiceBridgeFeature.class);
        config.register(HalloResource.class);
        config.packages("org.example.api", "org.example.service");

        // bridge the Guice container (Injector) into the HK2 container (ServiceLocator)
        Injector injector = Guice.createInjector(new ApiModule());
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);
    }

}