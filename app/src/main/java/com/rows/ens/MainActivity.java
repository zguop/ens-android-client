package com.rows.ens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rows.enslib.Ens;
import com.rows.enslib.EnsOptions;
import com.rows.enslib.EnsVerify;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String scheme = "ens://eoseosbank12";
        EnsOptions ensOptions = EnsOptions.getOptions().setEnsScheme(scheme);
        Ens.getEnsUrl(ensOptions, new EnsVerify() {
            @Override
            public void call(String ensInfo) {

            }
            @Override
            public void error(Throwable throwable) {

            }
        });
    }
}
