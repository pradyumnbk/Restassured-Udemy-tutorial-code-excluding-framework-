package com.rest.create.workspace.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

/* we can implement this annotation at both field level and class level */
//@JsonInclude(JsonInclude.Include.NON_NULL)/* this annotation is used when we dont want to include id in the payload. Id is used for assertion only */
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)/* this will exclude all the default value */
@JsonIgnoreProperties(value="id",allowSetters = true) /* here we are using for response thats why allowsetterss=true, I have taken screenshot for the reference  */
public class Workspace {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int i;/* this is not used but for demo purpose we have used it */
    private String name;
    private String type;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;


    public Workspace(){

    }

    public Workspace(String name,String type,String description){
        this.name=name;
        this.type=type;
        this.description=description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
