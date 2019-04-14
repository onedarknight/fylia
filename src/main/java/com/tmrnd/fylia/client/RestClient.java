/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.client;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tmrnd.fylia.api.Employee;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
 
@Produces(MediaType.TEXT_PLAIN)
@Path("/client/")
public class RestClient
{
    private Client client;
    
    final String username = "admin";
    final String password = "password123";
 
    public RestClient(Client client) {
        this.client = client;
    }
     
    @GET
    @Path("/employees/")
    public String getEmployees()
    {
        //Do not hard code in your application
        WebTarget webTarget = client.target("http://localhost:8080/employees");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME, username);
        invocationBuilder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD, password);
        Response response = invocationBuilder.get();
        @SuppressWarnings("rawtypes")
        ArrayList employees = response.readEntity(ArrayList.class);
        return employees.toString();
    }
     
    @GET
    @Path("/employees/{id}")
    public String getEmployeeById(@PathParam("id") int id)
    {
        //Do not hard code in your application
        WebTarget webTarget = client.target("http://localhost:8080/employees/"+id);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Employee employee = response.readEntity(Employee.class);
        return employee.toString();
    }
}