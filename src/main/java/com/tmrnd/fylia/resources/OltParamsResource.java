/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
 
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.tmrnd.fylia.db.OltParamsDB;
 
import com.tmrnd.fylia.db.EmployeeDB;
import com.tmrnd.fylia.api.Employee;
import com.tmrnd.fylia.auth.AuthUser;
import devicelayer.SnmpGet;
import io.dropwizard.auth.Auth;
import java.io.IOException;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
 
@Path("/oltparams")
@Produces(MediaType.APPLICATION_JSON)
public class OltParamsResource {
 
    private final Validator validator;
 
    public OltParamsResource(Validator validator) {
        this.validator = validator;
    }
 
    @PermitAll
    @GET
    public Response getOltParams(@Auth AuthUser user) throws IOException {
        SnmpGet oltParamGet = new SnmpGet();
        oltParamGet.getParam();
        return Response.ok(OltParamsDB.getOltparams()).build();
    }
 

}