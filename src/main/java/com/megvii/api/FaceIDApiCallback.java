package com.megvii.api;

import java.io.IOException;

public interface FaceIDApiCallback
{
    void onResponse(int apiId, int statusCode, String json);

    void onFailure(int apiId, IOException e);
}
