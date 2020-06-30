package com.manage.schoolnode.complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.manage.schoolnode.R;
import com.manage.schoolnode.attendance.AttendanceAdapter;
import com.manage.schoolnode.utils.ApiClient;
import com.manage.schoolnode.utils.ApiInterface;
import com.manage.schoolnode.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintActivity extends AppCompatActivity {

    List<ComplaintModel> complaints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        setTitle("Complaints");

        complaints = new ArrayList<>();

        final SharedPrefs sharedPrefs = new SharedPrefs(this);
        int studentId                 = sharedPrefs.getUserId();
        String accessToken            = sharedPrefs.getToken();

        final RecyclerView complaintRecyclerView = findViewById(R.id.recyclerview_complaint);
        LinearLayoutManager manager              = new LinearLayoutManager(this);
        final ComplaintAdapter mAdapter          = new ComplaintAdapter(complaints,this);

        complaintRecyclerView.setLayoutManager(manager);
        complaintRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        complaintRecyclerView.setAdapter(mAdapter);


        final ProgressDialog progressDialog = new ProgressDialog(ComplaintActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        String order = "{\"include\":\"teachers\",\"order\":\"complaintdate desc\"}";

        final Toast toast = Toast.makeText(this, "Failed to get data of student",Toast.LENGTH_SHORT);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ComplaintModel>> call = apiService.getComplaintsByStudentId(studentId,order,accessToken);
        call.enqueue(new Callback<List<ComplaintModel>>() {
            @Override
            public void onResponse(Call<List<ComplaintModel>> call, Response<List<ComplaintModel>> response) {
                complaints = response.body();
                Log.v("data",response.message());
                progressDialog.dismiss();
                mAdapter.setComplaints(complaints);
            }

            @Override
            public void onFailure(Call<List<ComplaintModel>> call, Throwable t) {
                toast.show();
            }
        });

    }
}
