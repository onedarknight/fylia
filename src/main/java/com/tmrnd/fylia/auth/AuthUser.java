/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmrnd.fylia.auth;


import java.security.Principal;
import java.util.Set;

public class AuthUser implements Principal {
    private final String name;

    private final Set<String> roles;

    public AuthUser(String name) {
        this.name = name;
        this.roles = null;
    }

    public AuthUser(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return (int) (Math.random() * 100);
    }

    public Set<String> getRoles() {
        return roles;
    }
}