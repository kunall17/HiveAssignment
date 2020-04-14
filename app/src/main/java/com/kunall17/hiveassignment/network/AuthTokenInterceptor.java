package com.kunall17.hiveassignment.network;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AuthTokenInterceptor implements Interceptor {

    private static final String TAG = AuthTokenInterceptor.class.getSimpleName();

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.header("user-key", "ae5a1591ff8b8181850534ca48587327");
        request = builder.build();
        return chain.proceed(request);
    }
}