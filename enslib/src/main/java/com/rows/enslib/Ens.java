package com.rows.enslib;

import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * auth aboom
 * date 2018/10/12
 */
public class Ens {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void getEnsUrl(EnsOptions ensOptions, final EnsVerify ensVerify) {
        if (ensOptions == null || ensVerify == null) {
            throw new NullPointerException("ensOptions is null or ensVerify is null");
        }
        Uri parse = Uri.parse(ensOptions.getEnsScheme());
        String scheme = parse.getScheme();
        if (TextUtils.isEmpty(scheme) || !scheme.equals("ens")) {
            ensVerify.error(new Throwable("ensScheme the path address is not correct"));
            return;
        }
        String host = parse.getHost();
        if (TextUtils.isEmpty(host)) {
            ensVerify.error(new Throwable("ensScheme the path address is not correct"));
            return;
        }
        String ensUrlHost = ensOptions.getEnsUrlHost();
        if (TextUtils.isEmpty(ensUrlHost)) {
            ensVerify.error(new Throwable("ensUrl the path address is not correct"));
            return;
        }
        //去获取地址
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("scope", host);
            jsonObject.put("code", "enserve.bank");
            jsonObject.put("table", "enstables");
            jsonObject.put("json", true);
        } catch (JSONException e) {
            e.printStackTrace();
            ensVerify.error(e);
        }

        OkHttpClient okHttpClient;
        if (ensOptions.getOkHttpClient() != null) {
            okHttpClient = ensOptions.getOkHttpClient();
        } else {
            okHttpClient = new OkHttpClient();
        }

        String json = jsonObject.toString();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(ensUrlHost + "/v1/chain/get_table_rows")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ensVerify.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    ensVerify.error(new Throwable("Request unsuccessful"));
                    return;
                }
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    ensVerify.error(new Throwable("responseBody is null"));
                    return;
                }
                String string = responseBody.string();
                if (TextUtils.isEmpty(string)) {
                    ensVerify.error(new Throwable("responseBody string is null"));
                    return;
                }
                try {
                    JSONObject resultJson = new JSONObject(string);
                    JSONArray rows = resultJson.getJSONArray("rows");
                    if (rows != null && rows.length() > 0) {
                        String dapp = rows.getJSONObject(0).getString("dapp");
                        ensVerify.call(dapp);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ensVerify.error(e);
                }
            }
        });

    }
}
