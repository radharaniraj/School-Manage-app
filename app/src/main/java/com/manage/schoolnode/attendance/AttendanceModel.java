package com.manage.schoolnode.attendance;

public class AttendanceModel {

    private String studentId;
    private String teacherId;
    private boolean isPresent;
    private String date;
    private String id;

    public String getStudentId() {
        return studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }
}
