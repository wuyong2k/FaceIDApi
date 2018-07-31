package com.megvii.api;

import android.support.annotation.NonNull;

public class FaceIDConfig
{
    /** api key */
    private String apiKey;
    /** api secret */
    private String apiSecret;
    /** 连接超时时长 单位：秒 */
    private int connectTimeout;
    /** 读 超时时长 单位：秒 */
    private int readTimeout;
    /** 写 超时时长 单位：秒 */
    private int writeTimeout;

    private FaceIDConfig() {}

    public String getApiKey()
    {
        return apiKey;
    }

    public String getApiSecret()
    {
        return apiSecret;
    }

    public int getConnectTimeout()
    {
        return connectTimeout;
    }

    public int getReadTimeout()
    {
        return readTimeout;
    }

    public int getWriteTimeout()
    {
        return writeTimeout;
    }

    public static class Builder
    {
        private @NonNull String apiKey = "";
        private @NonNull String apiSecret = "";
        private int connectTimeout = 20;
        private int readTimeout = 30;
        private int writeTimeout = 30;

        @NonNull
        public Builder setApiKey(@NonNull String key)
        {
            this.apiKey = key;
            return this;
        }

        @NonNull
        public Builder setApiSecret(@NonNull String secret)
        {
            this.apiSecret = secret;
            return this;
        }

        public Builder setConnectTimeout(int timeout)
        {
            this.connectTimeout = timeout;
            return this;
        }

        public Builder setReadTimeout(int timeout)
        {
            this.readTimeout = timeout;
            return this;
        }

        public Builder setWriteTimeout(int timeout)
        {
            this.writeTimeout = timeout;
            return this;
        }

        private void initConfig(FaceIDConfig config)
        {
            config.apiKey = this.apiKey;
            config.apiSecret = this.apiSecret;
            config.connectTimeout = this.connectTimeout;
            config.readTimeout = this.readTimeout;
            config.writeTimeout = this.writeTimeout;
        }

        public FaceIDConfig build()
        {
            FaceIDConfig config = new FaceIDConfig();
            initConfig(config);
            return config;
        }
    }
}
