package com.example.android_rd_code.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.example.android_rd_code.base.App;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Class is used to save user data in preference.
 */
public class PreferenceKeeper {

    private static PreferenceKeeper keeper;
    private SharedPreferences prefs;

    private PreferenceKeeper(Context context) {
        if (context != null)
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferenceKeeper getInstance() {
        if (keeper == null) {
            keeper = new PreferenceKeeper(App.getInstance());
        }
        return keeper;
    }


//    public void saveUserInfo(UserProfile user) {
//        if (user != null) {
//            prefs.edit().putString(AppConstant.PKN.USER_DATA, GsonUtils.getJson(user)).apply();
//        } else {
//            prefs.edit().putString(AppConstant.PKN.USER_DATA, null).apply();
//        }
//        AppConstant.USER_PROFILE = null;
//    }
//
//    public UserProfile getUserInfo() {
//        Type type = new TypeToken<UserProfile>() {
//        }.getType();
//        return GsonUtils.parseJson(prefs.getString(AppConstant.PKN.USER_DATA, "{}"), type);
//    }


    public void clearData() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public void savePermissionGranted(String key, boolean b) {
        prefs.edit().putBoolean(key, b).apply();
    }

    public void setVoteNowDialogShownTodayStatus(String key, String b) {
        prefs.edit().putString(key, b).apply();
    }

    public String getVoteNowDialogShownTodayStatus(String key) {
        return prefs.getString(key, "");
    }

    public boolean isPermissionGranted(String key) {
        return prefs.getBoolean(key, false);
    }

    public String getFCMToken() {
        String fcmToken = prefs.getString(AppConstant.PKN.FCM_TOKEN, "");
        if (TextUtils.isEmpty(fcmToken)) {
            fcmToken = "abcd";
        }
        return fcmToken;
    }

    public String getAccessToken() {
        String accessToken = prefs.getString(AppConstant.PKN.ACCESS_TOKEN, "");
        if (TextUtils.isEmpty(accessToken)) {
            accessToken = "";
        }
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        prefs.edit().putString(AppConstant.PKN.ACCESS_TOKEN, accessToken).apply();
    }

    public void setFCMToken(String fcmToken) {
        prefs.edit().putString(AppConstant.PKN.FCM_TOKEN, fcmToken).apply();
    }

    public void setUserLoggedIn(boolean state) {
        prefs.edit().putBoolean(AppConstant.PKN.USER_LOGGED_IN, state).apply();
    }

    public boolean isUserLoggedIn() {
        return prefs.getBoolean(AppConstant.PKN.USER_LOGGED_IN, false);
    }


    public void setTutorialInOne(boolean state) {
        prefs.edit().putBoolean(AppConstant.PKN.TUTORIAL_ONE, state).apply();
    }

    public boolean isTutorialOne() {
        return prefs.getBoolean(AppConstant.PKN.TUTORIAL_ONE, false);
    }

}
