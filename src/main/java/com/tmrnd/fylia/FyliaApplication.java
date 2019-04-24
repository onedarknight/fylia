package com.tmrnd.fylia;


import com.tmrnd.fylia.health.FyliaHealthCheck;
import com.tmrnd.fylia.resources.FyliaHealthCheckResource;
import com.tmrnd.fylia.resources.OltParamsResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FyliaApplication extends Application<FyliaConfiguration> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FyliaApplication.class);

    public static void main(final String[] args) throws Exception {
        new FyliaApplication().run(args);
    }

    @Override
    public String getName() {
        return "Fylia";
    }

    @Override
    public void initialize(final Bootstrap<FyliaConfiguration> bootstrap) {
        
    }

    @Override
    public void run(final FyliaConfiguration configuration,
                    final Environment environment) {
        
        LOGGER.info("FYLIA : Registering OLT Parameters REST resources");
        environment.jersey().register(new OltParamsResource(environment.getValidator()));
      
        environment.jersey().register(new FyliaHealthCheckResource(environment.healthChecks()));
    }
}
