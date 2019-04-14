/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.resources;

import java.util.Map.Entry;
import java.util.Set;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.codahale.metrics.health.HealthCheck.Result;
import com.codahale.metrics.health.HealthCheckRegistry;
 
@Produces(MediaType.APPLICATION_JSON)
@Path("/status")
public class FyliaHealthCheckResource
{
    private HealthCheckRegistry registry;
 
    public FyliaHealthCheckResource(HealthCheckRegistry registry) {
        this.registry = registry;
    }

    @Produces( MediaType.APPLICATION_JSON )
    @GET
    public Set<Entry<String, Result>> getStatus(){
        return registry.runHealthChecks().entrySet();
    }
}