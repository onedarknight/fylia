package com.tmrnd.fylia;

import com.tmrnd.fylia.auth.AuthUser;
import com.tmrnd.fylia.auth.FyliaAuthenticator;
import com.tmrnd.fylia.auth.FyliaAuthorizer;
import com.tmrnd.fylia.resources.EmployeeResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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
        
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<AuthUser>()
                                .setAuthenticator(new FyliaAuthenticator())
                                .setAuthorizer(new FyliaAuthorizer())
                                .setRealm("BASIC-AUTH-REALM")
                                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthUser.class));
        // TODO: implement application
    }

}
