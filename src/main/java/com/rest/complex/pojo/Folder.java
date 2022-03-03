package com.rest.complex.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Folder {
    private String name;
    List<RequestRoot> item;

    public Folder(){

    }
    public Folder(String name,List<RequestRoot> requestRoot){
        this.name=name;
        this.item =requestRoot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RequestRoot> getItem() {
        return item;
    }

    public void setItem(List<RequestRoot> item) {
        this.item = item;
    }



}
