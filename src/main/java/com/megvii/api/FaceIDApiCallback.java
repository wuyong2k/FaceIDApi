package com.megvii.api;

import java.io.IOException;

import okhttp3.Call;

public interface FaceIDApiCallback
{
    void onResponse(int apiId, int statusCode, String json);

    void onFailure(int apiId, Call call, IOException e);
}
