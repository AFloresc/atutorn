package com.inredec.atutorn.model.businesslayer.entities;

public class GithubRepo {
    String name;
    String owner;
    String url;

    @Override
    public String toString() {
        return(name + " " +  url);
    }
}
