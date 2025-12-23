package com.codewithdevani.store.model;

public class User {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private long id;
    private String name;

    public User(String name, long id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    private String email;
    
}
