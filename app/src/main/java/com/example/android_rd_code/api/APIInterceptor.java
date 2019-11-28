package com.example.android_rd_code.api;

import android.text.TextUtils;
import android.util.Log;
import com.example.android_rd_code.util.PreferenceKeeper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Class is used to passing user token at central level.
 */
public class APIInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = PreferenceKeeper.getInstance().getAccessToken();

        Log.i("MMMMMMMMM", "ACCESS TOKEN " + token);

        Request originalRequest = chain.request();
        if (TextUtils.isEmpty(token)) {
            Request.Builder builder = originalRequest.newBuilder();
            Request oldReq = builder
                    .addHeader("Authorization", "Basic Y2FyYnVkZHlfYWRtaW46Y2FyQGJ1ZGR5")
                    .build();
            return chain.proceed(oldReq);
        }

        Request.Builder builder = originalRequest.newBuilder();
        Request oldReq = builder
                .addHeader("accessToken", token)
                .addHeader("Authorization", "Basic Y2FyYnVkZHlfYWRtaW46Y2FyQGJ1ZGR5")
                .build();
        return chain.proceed(oldReq);
    }
}

