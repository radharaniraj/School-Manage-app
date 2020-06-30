package com.manage.schoolnode.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.manage.schoolnode.MainActivity;
import com.manage.schoolnode.R;
import com.manage.schoolnode.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SharedPrefs sharedPrefs = new SharedPrefs(SplashActivity.this);

        final ImageView rotatingLogo     = findViewById(R.id.logo_image);
        final Animation rotateAnimation  = AnimationUtils.loadAnimation(getBaseContext(),
                R.anim.push_left_in);
        final Animation fadeOutAnimation =
                AnimationUtils.loadAnimation(getBaseContext(), R.anim.push_left_in);

        rotatingLogo.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rotatingLogo.startAnimation(fadeOutAnimation);
                int userId = sharedPrefs.getUserId();
                String token = sharedPrefs.getToken();
                if (userId == 0 || token == null) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
