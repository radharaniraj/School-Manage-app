package com.manage.schoolnode.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.manage.schoolnode.utils.ApiClient;
import com.manage.schoolnode.utils.ApiInterface;
import com.manage.schoolnode.MainActivity;
import com.manage.schoolnode.R;
import com.manage.schoolnode.utils.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        final SharedPrefs sharedPrefs = new SharedPrefs(LoginActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _emailText = findViewById(R.id.input_email);
        _passwordText = findViewById(R.id.input_password);
        _loginButton = findViewById(R.id.btn_login);
        _loginButton = findViewById(R.id.btn_login);


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(sharedPrefs);
            }
        });

    }

    public void login(final SharedPrefs sharedPrefs) {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
        final Intent myIntent = new Intent(this, MainActivity.class);

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        final LinearLayout coordinatorLayout = findViewById(R.id.linear_layout_login);

        final Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Invalid Email or Password", Snackbar.LENGTH_LONG);

        String email    = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        JsonObject loginBody = new JsonObject();
        loginBody.addProperty("email",email);
        loginBody.addProperty("password",password);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<LoginModel> call = apiService.loginStudent(loginBody);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                int code = response.code();
                if(code==200) {
                    LoginModel user = response.body();
                    Log.v("login", response.body().toString());
                    sharedPrefs.saveToken(user.getId());
                    sharedPrefs.saveUserId(user.getUserId());
                    progressDialog.dismiss();
                    _loginButton.setEnabled(true);
                    startActivity(myIntent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else if(code==401){
                    progressDialog.dismiss();
                    snackbar.show();
                    _loginButton.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                snackbar.setText("Some error occured! Try Again");
                progressDialog.dismiss();
                snackbar.show();
                _loginButton.setEnabled(true);
            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
