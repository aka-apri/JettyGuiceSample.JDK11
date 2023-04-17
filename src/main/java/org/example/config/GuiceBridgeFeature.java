package org.example.config;

import com.google.inject.Injector;
import org.example.startup.StartupServiceListener;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.util.concurrent.ConcurrentHashMap;

@Provider
public class GuiceBridgeFeature implements Feature {

    private final static Logger logger = LoggerFactory.getLogger(GuiceBridgeFeature.class);

    @Inject
    private ServiceLocator serviceLocator;

    @Context
    private ServletContext servletContext;

    private static final String INJECTOR_NAME = Injector.class.getName();

    @Override
    public boolean configure(FeatureContext context) {
        logger.debug("Initializing GuiceIntoHK2Bridge...");
        Injector injector = (Injector) servletContext.getAttribute(INJECTOR_NAME);
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);

        return true;
    }
}
