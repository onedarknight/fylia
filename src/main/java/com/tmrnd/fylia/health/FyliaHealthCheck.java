/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.health;

import java.util.ArrayList;
 
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
import com.codahale.metrics.health.HealthCheck;
import java.util.Map;
import javax.ws.rs.core.NewCookie;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class FyliaHealthCheck extends HealthCheck {
    private static final Logger LOGGER = LoggerFactory.getLogger(FyliaHealthCheck.class);
    private final Client client;
    
    final String username = "admin";
    final String password = "password123";
 
    public FyliaHealthCheck(Client client) {
        super();
        this.client = client;
    }
 
    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target("http://localhost:8080/employees");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, username);
        invocationBuilder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, password);
        LOGGER.info("SURJYA : Health Check !!!");
        Response response = invocationBuilder.get();
        @SuppressWarnings("rawtypes")
        ArrayList employees = response.readEntity(ArrayList.class);
        
        System.out.println("surjya : health check");
        
        System.out.println("size : " + employees.size());
        
        for (final Map.Entry<String, NewCookie> entry : response.getCookies().entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        
        if(employees !=null && employees.size() > 0){
            LOGGER.info("FYLIA : Health Check is ok !!!");
            return Result.healthy();
        }
        LOGGER.info("FYLIA : Health Check is failed !!!");
        return Result.unhealthy("API Health Check Failed");
    }
}