package com.manage.schoolnode.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.manage.schoolnode.R;
import com.manage.schoolnode.homework.HomeworkActivity;
import com.manage.schoolnode.homework.HomeworkAdapter;
import com.manage.schoolnode.utils.ApiClient;
import com.manage.schoolnode.utils.ApiInterface;
import com.manage.schoolnode.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity {

    List<AttendanceModel> attendances;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        setTitle("Attendance");

        final SharedPrefs sharedPrefs = new SharedPrefs(this);
        int studentId = sharedPrefs.getUserId();
        String accessToken = sharedPrefs.getToken();
        attendances = new ArrayList<>();

        final RecyclerView attendanceRecyclerView = findViewById(R.id.recyclerview_attendance);
        LinearLayoutManager manager               = new LinearLayoutManager(this);
        final AttendanceAdapter mAdapter          = new AttendanceAdapter(attendances,this);

        attendanceRecyclerView.setLayoutManager(manager);
        attendanceRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        attendanceRecyclerView.setAdapter(mAdapter);
        final ProgressDialog progressDialog = new ProgressDialog(AttendanceActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        String order = "{\"order\":\"date desc\"}";

        final Toast toast = Toast.makeText(this, "Failed to get data of student",Toast.LENGTH_SHORT);

        ApiInterface apiService          = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AttendanceModel>> call = apiService.getAttendanceByStudentId(studentId,order,accessToken);

        call.enqueue(new Callback<List<AttendanceModel>>() {
            @Override
            public void onResponse(Call<List<AttendanceModel>> call, Response<List<AttendanceModel>> response) {
                attendances = response.body();
                progressDialog.dismiss();
                mAdapter.setAttendances(attendances);
            }

            @Override
            public void onFailure(Call<List<AttendanceModel>> call, Throwable t) {
                toast.show();
            }
        });
    }
}
