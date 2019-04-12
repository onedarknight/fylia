/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.auth;

import io.dropwizard.auth.Authorizer;
 
public class FyliaAuthorizer implements Authorizer<AuthUser>
{
    @Override
    public boolean authorize(AuthUser user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
