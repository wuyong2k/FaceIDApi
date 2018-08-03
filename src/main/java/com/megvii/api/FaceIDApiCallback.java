package com.megvii.api;

import java.io.IOException;

public interface FaceIDApiCallback
{
    /**
     * 发送的网络请求，收到服务器响应时回调
     * 包含非200的请求
     * @param apiId 请求api的id，用于区分是哪一个http请求结果
     * @param statusCode 状态码
     * @param json 返回json结果
     */
    void onResponse(int apiId, int statusCode, String json);

    /**
     * 发送的网络请求异常时回调
     * 非200的请求不会调用此接口，只处理请求异常
     * @param apiId 请求api的id，用于区分是哪一个http请求结果
     * @param e IO异常
     */
    void onFailure(int apiId, IOException e);
}
