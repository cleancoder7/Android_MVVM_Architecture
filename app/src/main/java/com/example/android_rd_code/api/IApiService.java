package com.example.android_rd_code.api;


import com.example.android_rd_code.base.BaseResponse;
import com.example.android_rd_code.ui.auth.login.loginResponseModel.LoginApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IApiService {



    @FormUrlEncoded
    @POST("carBuddy/api/v1/user/login")
    Call<BaseResponse<LoginApiResponse>> userLoginAPI(@Field("mob") String mob,
                                                      @Field("password") String password,
                                                      @Field("deviceID") String deviceID,
                                                      @Field("deviceToken") String deviceToken,
                                                      @Field("countryCode") String countryCode,
                                                      @Field("platform") int platform);



}
