package com.manage.schoolnode.homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.manage.schoolnode.MainActivity;
import com.manage.schoolnode.R;
import com.manage.schoolnode.utils.ApiClient;
import com.manage.schoolnode.utils.ApiInterface;
import com.manage.schoolnode.utils.SharedPrefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class HomeworkActivity extends AppCompatActivity {

    List<HomeworkModel> homeworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        setTitle("Homework");

        homeworks = new ArrayList<>();

        final SharedPrefs sharedPrefs = new SharedPrefs(this);
        String classId                = sharedPrefs.getClassIdKey();
        String accessToken            = sharedPrefs.getToken();

        final RecyclerView homeworkRecyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager             = new LinearLayoutManager(this);
        final HomeworkAdapter mAdapter          = new HomeworkAdapter(homeworks,this);
        homeworkRecyclerView.setLayoutManager(manager);
        homeworkRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        homeworkRecyclerView.setAdapter(mAdapter);



        final Toast toast = Toast.makeText(this, "Failed to get data of student",Toast.LENGTH_SHORT);

        final ProgressDialog progressDialog = new ProgressDialog(HomeworkActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        ApiInterface apiService        = ApiClient.getClient().create(ApiInterface.class);
        Call<List<HomeworkModel>> call = apiService.getHwByClassId(classId,accessToken);

        call.enqueue(new Callback<List<HomeworkModel>>() {
            @Override
            public void onResponse(Call<List<HomeworkModel>> call, Response<List<HomeworkModel>> response) {
                homeworks = response.body();
                progressDialog.dismiss();
                mAdapter.setHomeworks(homeworks);
            }

            @Override
            public void onFailure(Call<List<HomeworkModel>> call, Throwable t) {
                toast.show();
            }
        });


    }
}
