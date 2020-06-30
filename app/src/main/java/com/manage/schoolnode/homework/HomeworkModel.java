package com.manage.schoolnode.homework;

public class HomeworkModel {
    private String hwDescription;

    private String classId;

    private String teacherId;

    private String id;

    private String subjectId;

    private String Date;

    public String getHwDescription ()
    {
        return hwDescription;
    }

    public void setHwDescription (String hwDescription)
    {
        this.hwDescription = hwDescription;
    }

    public String getClassId ()
    {
        return classId;
    }

    public void setClassId (String classId)
    {
        this.classId = classId;
    }

    public String getTeacherId ()
    {
        return teacherId;
    }

    public void setTeacherId (String teacherId)
    {
        this.teacherId = teacherId;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSubjectId ()
    {
        return subjectId;
    }

    public void setSubjectId (String subjectId)
    {
        this.subjectId = subjectId;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [hwDescription = "+hwDescription+", classId = "+classId+", teacherId = "+teacherId+", id = "+id+", subjectId = "+subjectId+", Date = "+Date+"]";
    }
}
