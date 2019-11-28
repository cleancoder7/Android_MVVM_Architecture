package com.example.android_rd_code.api;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.example.android_rd_code.R;
import com.example.android_rd_code.base.App;
import com.example.android_rd_code.base.BaseResponse;
import com.example.android_rd_code.model.base.Errors;
import com.example.android_rd_code.ui.auth.login.LoginActivity;
import com.example.android_rd_code.util.AppConstant;
import com.example.android_rd_code.util.AppUtil;
import com.example.android_rd_code.util.PreferenceKeeper;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * All api method
 *
 * @param <T> model class
 */
public class APICallManager<T> implements Callback<BaseResponse<T>>, APIStatusCode {

    private Handler handler;
    private APIType mCallType;
    private APICallHandler mAPICallHandler;
    private Call mCall;
    private Runnable runnable;
    private IApiService repoRepository;

    public APICallManager(APIType callType, APICallHandler callHandler, IApiService repoRepository) {
        this.mCallType = callType;
        this.mAPICallHandler = callHandler;
        this.repoRepository = repoRepository;
        if (runnable != null && handler != null) {
            handler.removeCallbacks(runnable);
        }

        if (handler == null) {
            handler = new Handler();
        }

        setRunnable();

        handler.postDelayed(runnable, 80000);
    }

    private void setRunnable() {
        runnable = new Runnable() {
            @Override
            public void run() {
                int errorCode = 0;
                String message = "Network has some problem. please try again...";
                Errors errors = new Errors();
                errors.errorMessage = message;
                if (mAPICallHandler != null) {
                    mAPICallHandler.onFailure(mCallType, errorCode, errors);
                    handler.removeCallbacks(this);
                }
            }
        };
    }

    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        if (runnable != null && handler != null) {
            handler.removeCallbacks(runnable);
        }
        if (response.code() == OK || response.code() == CREATED || response.code() == NO_CONTENT) {
            if (mAPICallHandler != null) {
                BaseResponse<T> body = response.body();
                Log.i("EEEEEEEEE", " DATA " + body);
                if (body.statusCode == 1 && body.responseData != null) {
                    mAPICallHandler.onSuccess(mCallType, body.responseData);
                } else {
                    Errors r = body.error;
                    if (r == null || r.errorCode == 30) {
                        //logout user access token invalid
                    } else if (r.errorCode == 2) { //access token expired
                        AppUtil.showToast(r.errorMessage);
                        PreferenceKeeper.getInstance().clearData();
                        Intent intent = new Intent(App.getInstance(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        App.getInstance().startActivity(intent);
                    } else {
                        mAPICallHandler.onFailure(mCallType, response.code(), body.error);
                    }
                }
            }
        } else if (response.code() == AUTHENTICATION_FAILED) {
            Errors errors = new Errors();
            String errorMessage = App.getInstance().getResources().getString(R.string.invalid_credentials);
            errors.errorMessage = errorMessage;
            if (mAPICallHandler != null) {
                mAPICallHandler.onFailure(mCallType, response.code(), errors);
            }
        } else if (response.code() == BAD_REQUEST) {
            Errors errors = new Errors();
            String errorMessage = App.getInstance().getResources().getString(R.string.bad_request);
            errors.errorMessage = errorMessage;
            mAPICallHandler.onFailure(mCallType, response.code(), errors);
        } else {
            Errors errors = new Errors();
            String errorMessage = App.getInstance().getResources().getString(R.string.error_something_wrong);
            errors.errorMessage = errorMessage;

            if (mAPICallHandler != null) {
                mAPICallHandler.onFailure(mCallType, response.code(), errors);
            }
        }
    }

    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable throwable) {
        if (runnable != null && handler != null) {
            handler.removeCallbacks(runnable);
        }
        int errorCode = 0;
        String message = "";
        if ((throwable instanceof UnknownHostException || throwable instanceof SocketException || throwable instanceof SocketTimeoutException)) {
            message = App.getInstance().getResources().getString(R.string.error_something_wrong);
        } else {
            message = throwable.getMessage();
        }

        Errors errors = new Errors();
        errors.errorMessage = message;
        if (mAPICallHandler != null) {
            mAPICallHandler.onFailure(mCallType, errorCode, errors);
        }
    }


    public void userLoginApi(String mob, String password, String countryCode) {
        String deviceId = AppUtil.getDeviceId();
        String deviceToken = PreferenceKeeper.getInstance().getFCMToken();
        mCall = repoRepository.userLoginAPI(mob, password, deviceId, deviceToken, countryCode, AppConstant.PLATFORM);
        if (mCall != null) {
            mCall.enqueue(this);
        }
    }


}

