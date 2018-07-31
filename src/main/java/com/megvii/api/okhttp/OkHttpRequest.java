package com.megvii.api.okhttp;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpRequest
{
    /**
     * generate a get request
     * @param fullUrl like: https://www.icheero.com?key1=val1&key2=val2&...
     * @return a get request
     */
    public static Request createGetRequest(@NonNull final String fullUrl)
    {
        return new Request.Builder().url(fullUrl).build();
    }

    /**
     * generate a get request
     * @param baseUrl like: https://www.icheero.com
     * @param paramMap lile: key1, val1; key2, val2 ...
     * @return a get request
     */
    public static Request createGetRequest(@NonNull final String baseUrl, final Map<String, Object> paramMap)
    {
        StringBuilder sb = new StringBuilder(baseUrl);
        if (paramMap != null)
        {
            sb.append("?");
            for (Map.Entry<String, Object> entry : paramMap.entrySet())
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return createGetRequest(sb.toString().substring(0, sb.length() - 1));
    }

    /**
     * generate a get request
     * @param baseUrl like: https://www.icheero.com
     * @param paramStr like: key1=val1&key2=val2&...
     * @return a get request
     */
    public static Request createGetRequest(@NonNull String baseUrl, String paramStr)
    {
        if (!TextUtils.isEmpty(paramStr))
            baseUrl += ("?" + paramStr);
        return createGetRequest(baseUrl);
    }

    public static Request createPostRequest(@NonNull final String baseUrl, @NonNull Map<String, String> paramMap)
    {

        return null;
    }

    public static Request createFilePostRequest(@NonNull final String baseUrl, @NonNull Map<String, Object> paramMap, String contentType)
    {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : paramMap.entrySet())
        {
            Object value = entry.getValue();
            if (value instanceof File)
            {
                File file = (File) value;
                builder.addFormDataPart(entry.getKey(), file.getName(), RequestBody.create(MediaType.parse(contentType), file));
            }
            else
                builder.addFormDataPart(entry.getKey(), value.toString());
        }
        return new Request.Builder().url(baseUrl).post(builder.build()).build();
    }
}
