package com.manage.schoolnode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.manage.schoolnode.Announcement.AnnouncementActivity;
import com.manage.schoolnode.attendance.AttendanceActivity;
import com.manage.schoolnode.complaint.ComplaintActivity;
import com.manage.schoolnode.homework.HomeworkActivity;
import com.manage.schoolnode.login.LoginActivity;
import com.manage.schoolnode.utils.ApiClient;
import com.manage.schoolnode.utils.ApiInterface;
import com.manage.schoolnode.utils.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout HomeWorkLL, AttendanceLL, AnnouncementLL, ComplaintLL, ProfileLL;
    Button LogoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.school_name);

        LogoutButton = findViewById(R.id.logout_button);
        final Intent myIntent = new Intent(this, LoginActivity.class);


        final SharedPrefs sharedPrefs = new SharedPrefs(MainActivity.this);
        final String Token = sharedPrefs.getToken();
        int Id = sharedPrefs.getUserId();

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        String order = "{\"include\":\"classDetail\"}";

        final Toast toast = Toast.makeText(this, "Failed to get data of student",Toast.LENGTH_SHORT);

        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<StudentModel> call = apiService.getStudentById(Id,order, Token);

        call.enqueue(new Callback<StudentModel>() {
            @Override
            public void onResponse(Call<StudentModel> call, Response<StudentModel> response) {
                progressDialog.dismiss();
                sharedPrefs.saveClassId(response.body().getClassId());
                sharedPrefs.saveEmail(response.body().getEmail());
                sharedPrefs.saveFathersName(response.body().getFathersName());
                sharedPrefs.saveRollNumber(response.body().getRollNumber());
                sharedPrefs.saveClassName(response.body().getClassDetail().getClassName());
                sharedPrefs.saveFullName(response.body().getName());
            }

            @Override
            public void onFailure(Call<StudentModel> call, Throwable t) {
                toast.show();
                progressDialog.dismiss();
                startActivity(myIntent);
                finish();
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.setText(R.string.logout_toast);
                toast.show();
                Call<Void> call = apiService.logoutStudent(Token);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==204){
                            sharedPrefs.clearLogin();
                            startActivity(myIntent);
                            finish();
                        }
                        else{
                            sharedPrefs.clearLogin();
                            startActivity(myIntent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.v("urlfail",t.toString());
                        toast.setText("Unable to logout! Please Try Again");
                        toast.show();
                    }
                });
            }
        });



        HomeWorkLL = findViewById(R.id.homework_ll);
        final Intent hwIntent = new Intent(this, HomeworkActivity.class);
        HomeWorkLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(hwIntent);
            }
        });

        AttendanceLL = findViewById(R.id.attendance_ll);
        final Intent attendanceIntent = new Intent(this, AttendanceActivity.class);
        AttendanceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(attendanceIntent);
            }
        });

        AnnouncementLL = findViewById(R.id.announcement_ll);
        final Intent announcementIntent = new Intent(this, AnnouncementActivity.class);
        AnnouncementLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(announcementIntent);
            }
        });

        ComplaintLL = findViewById(R.id.complaint_ll);
        final Intent complaintIntent = new Intent(this, ComplaintActivity.class);
        ComplaintLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(complaintIntent);
            }
        });

        ProfileLL = findViewById(R.id.profile_ll);
        final Intent profileIntent = new Intent(this,ProfileActivity.class);
        ProfileLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
            }
        });


    }
}
