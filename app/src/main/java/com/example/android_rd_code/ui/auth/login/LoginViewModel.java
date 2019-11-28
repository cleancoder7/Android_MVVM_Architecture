package com.example.android_rd_code.ui.auth.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_rd_code.api.APICallHandler;
import com.example.android_rd_code.api.APICallManager;
import com.example.android_rd_code.api.APIType;
import com.example.android_rd_code.api.IApiService;
import com.example.android_rd_code.model.base.CommonApiResponse;
import com.example.android_rd_code.model.base.Errors;
import com.example.android_rd_code.ui.auth.login.loginResponseModel.LoginApiResponse;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel implements APICallHandler {

    private final IApiService iApiService;

    public MutableLiveData<CommonApiResponse> changePasswordSuccess = new MutableLiveData<>();
    public MutableLiveData<LoginApiResponse> success = new MutableLiveData<>();
    public MutableLiveData<Errors> error = new MutableLiveData<>();

    @Inject
    public LoginViewModel(IApiService repoRepository) {
        this.iApiService = repoRepository;
    }


    public void onUserLogin(String mob, String password, String countryCode) {
        APICallManager apiCallManager = new APICallManager(APIType.LOGIN, this, iApiService);
        apiCallManager.userLoginApi(mob, password, countryCode);
    }


    @Override
    public void onFailure(APIType apiType, int code, Errors errors) {
        error.setValue(errors);
    }

    @Override
    public void onSuccess(APIType apiType, Object response) {

        switch (apiType) {

            case LOGIN:
                LoginApiResponse loginResponse = (LoginApiResponse) response;
                success.setValue(loginResponse);
                break;


        }

    }
}
