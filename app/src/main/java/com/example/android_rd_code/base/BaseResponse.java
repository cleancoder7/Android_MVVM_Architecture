package com.example.android_rd_code.base;

import com.example.android_rd_code.model.base.Errors;

public class BaseResponse<T> {

    public int statusCode;
    public T responseData;
    public Errors error;
}
