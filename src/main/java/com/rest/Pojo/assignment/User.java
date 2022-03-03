package com.rest.Pojo.assignment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

public class User {
    private String name;
    private String username;
    private String email;
    Address address;
    Geo geo;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int id;

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public User(String name,String username,String email,Address address,Geo geo){
        this.name=name;
        this.username=username;
        this.email=email;
        this.address=address;
        this.geo=geo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getRootaddress() {
        return address;
    }

    public void setRootaddress(Address address) {
        this.address = address;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

}
