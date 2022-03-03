package com.rest.complex.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootCollection {
    Collection collection;
    public RootCollection(){

    }
    public RootCollection(Collection collection){
        this.collection=collection;
    }
    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }


}
