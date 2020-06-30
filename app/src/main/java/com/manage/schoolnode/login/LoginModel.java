package com.manage.schoolnode.login;

public class LoginModel {
    private String created;

    private String id;

    private String ttl;

    private int userId;

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTtl ()
    {
        return ttl;
    }

    public void setTtl (String ttl)
    {
        this.ttl = ttl;
    }

    public int getUserId ()
    {
        return userId;
    }

    public void setUserId (int userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo created = "+created+", id = "+id+", ttl = "+ttl+", userId = "+userId+"]";
    }
}

