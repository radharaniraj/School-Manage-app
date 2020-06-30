package com.manage.schoolnode.utils;

import android.content.Context;
import android.content.SharedPreferences;

@SuppressWarnings("SpellCheckingInspection")
public class SharedPrefs {

    private static final String myPrefs = "myPrefs";
    private static final String UserNameKey = "userName";
    private static final String EmailKey = "emailId";
    private static final String FullNameKey = "fullName";
    private static final String TokenKey = "token";
    private static final String UserId = "userId";
    private static final String PassKey = "userPass";
    private static final String ExpiresOnKey = "expiresOn";
    private static final String FathersNameKey = "fathersName";
    private static final String ClassIdKey = "classId";
    private static final String RollNumberKey = "rollNumber";
    private static final String ClassnameKey = "rollNumber";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    public SharedPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }



    //Save USerName in sharedPref and get Username
    public void saveUserName(String key) {
        editor.putString(UserNameKey, key);
        editor.commit();
    }


    public String getUserName() {
        return sharedPreferences.getString(UserNameKey, null);
    }


    public void saveClassName(String key) {
        editor.putString(ClassnameKey,key);
        editor.commit();
    }

    public String getClassnameKey() {
        return sharedPreferences.getString(ClassnameKey,null);
    }

    //Save Email in sharedPref and get Email
    public void saveEmail(String email) {
        editor.putString(EmailKey, email);
        editor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(EmailKey, null);
    }


    //Save firstName and lastName as fullName and get fullName
    public void saveFullName(String firstName) {
        editor.putString(FullNameKey, firstName);
        editor.commit();
    }

    public String getfullName() {
        return sharedPreferences.getString(FullNameKey, null);
    }

    //Save Token and get Token when required
    public void saveToken(String token) {
        editor.putString(TokenKey, token);
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(TokenKey, null);
    }

    public void saveUserId(int userId) {
        editor.putInt(UserId, userId);
        editor.commit();
    }


    public int getUserId() {
        return sharedPreferences.getInt(UserId, 0);
    }

    public void savePass(String password) {
        editor.putString(PassKey, password);
        editor.commit();
    }

    public String getPass() {
        return sharedPreferences.getString(PassKey, null);
    }

    public void saveTokenExpiresOn(String ExpiresOn) {
        editor.putString(ExpiresOnKey, ExpiresOn);
        editor.commit();
    }

    public String getClassIdKey() {
        return sharedPreferences.getString(ClassIdKey, null);
    }

    public void saveClassId(String ClassId) {
        editor.putString(ClassIdKey, ClassId);
        editor.commit();
    }

    public String getRollNumberKey() {
        return sharedPreferences.getString(RollNumberKey, null);
    }

    public void saveRollNumber(String RollNumber) {
        editor.putString(RollNumberKey, RollNumber);
        editor.commit();
    }

    public String getFathersNameKey() {
        return sharedPreferences.getString(FathersNameKey, null);
    }

    public void saveFathersName(String FathersName) {
        editor.putString(FathersNameKey, FathersName);
        editor.commit();
    }



    public String getTokenExpiresOn() {
        return sharedPreferences.getString(ExpiresOnKey, null);
    }

    public void clearLogin() {
        editor.putString(UserNameKey, null);
        editor.putString(EmailKey, null);
        editor.putString(FullNameKey, null);
        editor.putString(TokenKey, null);
        editor.putInt(UserId, 0);
        editor.commit();
    }
}
