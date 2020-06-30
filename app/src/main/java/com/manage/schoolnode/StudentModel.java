package com.manage.schoolnode;

public class StudentModel {

    private Boolean emailVerified;

    private String classId;

    private String fathersName;

    private String name;

    private String rollNumber;

    private String id;

    private String email;

    private String username;

    private ClassModel classDetail;

    public ClassModel getClassDetail() {
        return classDetail;
    }

    public void setClassDetail(ClassModel classDetail) {
        this.classDetail = classDetail;
    }

    public Boolean getEmailVerified ()
    {
        return emailVerified;
    }

    public void setEmailVerified (Boolean emailVerified)
    {
        this.emailVerified = emailVerified;
    }

    public String getClassId ()
    {
        return classId;
    }

    public void setClassId (String classId)
    {
        this.classId = classId;
    }

    public String getFathersName ()
    {
        return fathersName;
    }

    public void setFathersName (String fathersName)
    {
        this.fathersName = fathersName;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getRollNumber ()
    {
        return rollNumber;
    }

    public void setRollNumber (String rollNumber)
    {
        this.rollNumber = rollNumber;
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
        return "ClassPojo [emailVerified = "+emailVerified+", classId = "+classId+", fathersName = "+fathersName+", name = "+name+", rollNumber = "+rollNumber+",id = "+id+", email = "+email+", username = "+username+"]";
    }
}
