package com.rows.enslib;

import okhttp3.OkHttpClient;

/**
 * auth aboom
 * date 2018/10/15
 */
public class EnsOptions {

    private String ensScheme;

    private String ensUrlHost;

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

    public String getEnsUrlHost() {
        return ensUrlHost;
    }

    public void setEnsUrlHost(String ensUrlHost) {
        this.ensUrlHost = ensUrlHost;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public EnsOptions setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }
}
