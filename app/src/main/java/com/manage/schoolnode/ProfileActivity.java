package com.manage.schoolnode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.manage.schoolnode.utils.SharedPrefs;

public class ProfileActivity extends AppCompatActivity {

    TextView ClassTv, NameTv, FathernameTv, RollNumberTv, EmailTv, PhoneNumberTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final SharedPrefs sharedPrefs = new SharedPrefs(this);

        ClassTv       = findViewById(R.id.class_tv);
        NameTv        = findViewById(R.id.tv_name);
        FathernameTv  = findViewById(R.id.father_name_tv);
        RollNumberTv  = findViewById(R.id.roll_number_tv);
        EmailTv       = findViewById(R.id.email_txt);
        PhoneNumberTv = findViewById(R.id.phone_number_tv);


        String Name       = sharedPrefs.getfullName();
        String ClassName  = sharedPrefs.getClassnameKey();
        String FatherName = sharedPrefs.getFathersNameKey();
        String RollNumber = sharedPrefs.getRollNumberKey();
        String Email      = sharedPrefs.getEmail();

        ClassTv.setText(ClassName);
        NameTv.setText(Name);
        FathernameTv.setText(FatherName);
        RollNumberTv.setText(RollNumber);
        EmailTv.setText(Email);
        PhoneNumberTv.setText("+91-848484848");
    }
}
