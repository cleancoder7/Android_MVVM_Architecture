package com.example.android_rd_code.api;


import com.example.android_rd_code.model.base.Errors;

/**
 * Base api handler
 *
 * @param <T> current model class
 */
public interface APICallHandler<T> {

    // api type and model response
    void onSuccess(APIType apiType, T response);

    void onFailure(APIType apiType, int code, Errors errors);
}
