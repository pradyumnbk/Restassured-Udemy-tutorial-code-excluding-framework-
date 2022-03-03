package com.rest.complex.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
    private Object url;
    private String method;
    List<Header> header;
    Body body;

    public Request(){

    }
    public Request(Object url,String method,List<Header> header,Body body){
        this.url=url;
        this.method=method;
        this.header=header;
        this.body=body;
    }
    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }



}
