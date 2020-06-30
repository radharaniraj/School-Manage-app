package com.manage.schoolnode.utils;

import com.google.gson.JsonObject;
import com.manage.schoolnode.Announcement.AnnouncementModel;
import com.manage.schoolnode.StudentModel;
import com.manage.schoolnode.attendance.AttendanceModel;
import com.manage.schoolnode.complaint.ComplaintModel;
import com.manage.schoolnode.homework.HomeworkModel;
import com.manage.schoolnode.login.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("students/login")
    Call<LoginModel> loginStudent(@Body JsonObject jsonObject);

    @GET("students/{id}")
    Call<StudentModel> getStudentById(@Path("id") int studentId,@Query("filter") String order, @Query("access_token") String accesToken);

    @GET("classes/{id}/homework")
    Call<List<HomeworkModel>> getHwByClassId(@Path("id") String classId, @Query("access_token") String accesToken);

    @GET("students/{id}/attendances")
    Call<List<AttendanceModel>> getAttendanceByStudentId(@Path("id") int classId,@Query("filter") String order, @Query("access_token") String accesToken);

    @GET("Announcements")
    Call<List<AnnouncementModel>> getAnnouncement(@Query("filter") String order, @Query("access_token") String accesToken);

    @GET("students/{id}/complaint")
    Call<List<ComplaintModel>> getComplaintsByStudentId(@Path("id") int studentId, @Query("filter") String order, @Query("access_token") String accesToken);

    @GET("students/logout")
    Call<Void> logoutStudent(@Query("access_token") String accesToken);

}
