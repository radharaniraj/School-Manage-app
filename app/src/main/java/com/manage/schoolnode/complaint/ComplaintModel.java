package com.manage.schoolnode.complaint;

import com.manage.schoolnode.Teachers;

public class ComplaintModel {
    private String complaint_message;

    private String studentId;

    private String complaintDate;

    private String teacherId;

    private Teachers teachers;

    private String id;

    public String getComplaint_message ()
    {
        return complaint_message;
    }

    public void setComplaint_message (String complaint_message)
    {
        this.complaint_message = complaint_message;
    }

    public String getStudentId ()
    {
        return studentId;
    }

    public void setStudentId (String studentId)
    {
        this.studentId = studentId;
    }

    public String getComplaintDate ()
    {
        return complaintDate;
    }

    public void setComplaintDate (String complaintDate)
    {
        this.complaintDate = complaintDate;
    }

    public String getTeacherId ()
    {
        return teacherId;
    }

    public void setTeacherId (String teacherId)
    {
        this.teacherId = teacherId;
    }

    public Teachers getTeachers ()
    {
        return teachers;
    }

    public void setTeachers (Teachers teachers)
    {
        this.teachers = teachers;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [complaint_message = "+complaint_message+", studentId = "+studentId+", complaintDate = "+complaintDate+", teacherId = "+teacherId+", teachers = "+teachers+", id = "+id+"]";
    }
}
