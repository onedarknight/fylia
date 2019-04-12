package com.tmrnd.fylia;

import com.tmrnd.fylia.resources.EmployeeResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
        // TODO: application initialization
    }

    @Override
    public void run(final FyliaConfiguration configuration,
                    final Environment environment) {
        
        LOGGER.info("FYLIA : Registering REST resources");
        environment.jersey().register(new EmployeeResource(environment.getValidator()));
        // TODO: implement application
    }

}
