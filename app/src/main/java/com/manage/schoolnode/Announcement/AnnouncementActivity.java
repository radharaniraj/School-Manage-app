package com.manage.schoolnode.Announcement;

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

public class AnnouncementActivity extends AppCompatActivity {

    List<AnnouncementModel> announcements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        setTitle("Notice Board");

        final SharedPrefs sharedPrefs = new SharedPrefs(this);
        String accessToken = sharedPrefs.getToken();

        final RecyclerView announcementRecyclerView = findViewById(R.id.recyclerview_announcement);
        LinearLayoutManager manager                 = new LinearLayoutManager(this);
        final AnnouncementAdapter mAdapter          = new AnnouncementAdapter(announcements,
                this);

        announcements = new ArrayList<>();

        announcementRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        announcementRecyclerView.setAdapter(mAdapter);
        announcementRecyclerView.setLayoutManager(manager);

        final ProgressDialog progressDialog = new ProgressDialog(AnnouncementActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        String order = "{\"order\":\"date desc\"}";
        final Toast toast = Toast.makeText(this, "Failed to get data of student", Toast.LENGTH_SHORT);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<AnnouncementModel>> call = apiService.getAnnouncement(order, accessToken);
        call.enqueue(new Callback<List<AnnouncementModel>>() {
            @Override
            public void onResponse(Call<List<AnnouncementModel>> call, Response<List<AnnouncementModel>> response) {
                announcements = response.body();
                progressDialog.dismiss();
                mAdapter.setAnnouncements(announcements);
            }

            @Override
            public void onFailure(Call<List<AnnouncementModel>> call, Throwable t) {
                toast.show();
            }
        });
    }
}
