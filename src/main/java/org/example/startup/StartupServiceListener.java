package org.example.startup;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.example.config.GuiceBridgeFeature;
import org.example.config.guice.ApiModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StartupServiceListener extends GuiceServletContextListener implements HttpSessionListener{

    private Logger logger = LoggerFactory.getLogger(StartupServiceListener.class);

    @Override
    protected Injector getInjector() {
        logger.info("Creating Injector");

        List<AbstractModule> modules = new ArrayList<>();

        try {
            modules.add((ServletModule) Class.forName(ApiModule.class.getName()).newInstance());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        var injector = Guice.createInjector(modules);

        return injector;
    }

    private List<Class<?>> loadClasses(List<String> modules){
        return modules.stream().map(name -> {
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
