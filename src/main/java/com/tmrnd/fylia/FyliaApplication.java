package com.tmrnd.fylia;

import com.tmrnd.fylia.auth.AuthUser;
import com.tmrnd.fylia.auth.FyliaAuthenticator;
import com.tmrnd.fylia.auth.FyliaAuthorizer;
import com.tmrnd.fylia.client.RestClient;
import com.tmrnd.fylia.health.FyliaHealthCheck;
import com.tmrnd.fylia.resources.EmployeeResource;
import com.tmrnd.fylia.resources.FyliaHealthCheckResource;
import com.tmrnd.fylia.resources.OltParamsResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.http.HttpClient;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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
        // TODO: application initialization
    }

    @Override
    public void run(final FyliaConfiguration configuration,
                    final Environment environment) {
        
        LOGGER.info("FYLIA : Registering REST resources");
        environment.jersey().register(new EmployeeResource(environment.getValidator()));
        
        LOGGER.info("FYLIA : Registering OLT Parameters REST resources");
        environment.jersey().register(new OltParamsResource(environment.getValidator()));
        
        //final HttpClient client = new HttpClientBuilder(e).build("DemoRESTClient");
        final Client client = new JerseyClientBuilder(environment).build("DemoRESTClient");
        environment.jersey().register(new RestClient(client));
        
//        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<AuthUser>()
//                                .setAuthenticator(new FyliaAuthenticator())
//                                .setAuthorizer(new FyliaAuthorizer())
//                                .setRealm("BASIC-AUTH-REALM")
//                                .buildAuthFilter()));
//        environment.jersey().register(RolesAllowedDynamicFeature.class);
//        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthUser.class));
        
        environment.healthChecks().register("APIHealthCheck", new FyliaHealthCheck(client));
        environment.jersey().register(new FyliaHealthCheckResource(environment.healthChecks()));
        // TODO: implement application
    }
}
