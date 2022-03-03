package com.rest.create.workspace.pojo;

public class RootWorkspace {
    Workspace workspace;

   public RootWorkspace(){

   }
    public RootWorkspace(Workspace workspace){
        this.workspace=workspace;
    }
    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }
}
