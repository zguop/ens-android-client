package com.rows.enslib;

import okhttp3.OkHttpClient;

/**
 * auth aboom
 * date 2018/10/15
 */
public class EnsOptions {

    private String ensScheme;

    private String ensUrl;

    private OkHttpClient okHttpClient;

    private EnsOptions() {

    }

    public static EnsOptions getOptions() {
        return new EnsOptions();
    }

    public String getEnsScheme() {
        return ensScheme;
    }

    public EnsOptions setEnsScheme(String ensScheme) {
        this.ensScheme = ensScheme;
        return this;
    }

    public String getEnsUrl() {
        return ensUrl;
    }

    public EnsOptions setEnsUrl(String ensUrl) {
        this.ensUrl = ensUrl;
        return this;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public EnsOptions setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }
}
