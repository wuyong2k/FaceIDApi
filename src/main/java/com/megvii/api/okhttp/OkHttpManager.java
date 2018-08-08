package com.megvii.api.okhttp;

import android.support.annotation.NonNull;

import com.megvii.api.FaceIDConfig;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpManager
{
    private static volatile OkHttpManager mInstance;
    private OkHttpClient mOkHttpClient;

    private OkHttpManager()
    {

    }

    public static OkHttpManager getInstance()
    {
        if (mInstance == null)
        {
            synchronized (OkHttpManager.class)
            {
                if (mInstance == null)
                    mInstance = new OkHttpManager();
            }
        }
        return mInstance;
    }

    public void init(FaceIDConfig config)
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(config.getConnectTimeout(), TimeUnit.SECONDS);
        builder.readTimeout(config.getReadTimeout(), TimeUnit.SECONDS);
        builder.writeTimeout(config.getWriteTimeout(), TimeUnit.SECONDS);
        // support https
        builder.hostnameVerifier(new HostnameVerifier()
        {
            @Override
            public boolean verify(String hostname, SSLSession session)
            {
                return true;
            }
        });
        // trust all the https point
        builder.sslSocketFactory(initSSLSocketFactory(), initX509TrustManager());
        mOkHttpClient = builder.build();
    }

    /**
     * 同步请求
     * @param request http request
     * @return response
     * @throws IOException
     */
    public Response sync(@NonNull Request request) throws IOException
    {
        return mOkHttpClient.newCall(request).execute();
    }

    public void async(@NonNull Request request, @NonNull Callback callback)
    {
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    private SSLSocketFactory initSSLSocketFactory()
    {
        SSLSocketFactory ssfFactory = null;
        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{initX509TrustManager()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ssfFactory;
    }

    private X509TrustManager initX509TrustManager()
    {
        return new X509TrustManager()
        {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
            {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
            {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers()
            {
                return new java.security.cert.X509Certificate[]{ };
            }
        };
    }
}
