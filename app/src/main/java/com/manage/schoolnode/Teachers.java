package com.manage.schoolnode;

public class Teachers {
    private boolean emailVerified;

    private String name;

    private String realm;

    private String isAdmin;

    private String id;

    private String email;

    private String username;

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getIsAdmin ()
    {
        return isAdmin;
    }

    public void setIsAdmin (String isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "ClassPojo emailVerified = "+emailVerified+", name = "+name+", realm = "+realm+", isAdmin = "+isAdmin+", id = "+id+", email = "+email+", username = "+username+"]";
    }
}
