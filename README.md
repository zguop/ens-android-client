# ENS



## 使用

```groovy
dependencies{
    compile 'com.rows.enslib:enslib:0.1.3'  //最新版本
}
```

```groovy
String scheme = "ens://eoseosbank12";
EnsOptions ensOptions = EnsOptions.getOptions()
            .setEnsScheme(scheme)
            .setEnsUrl("")
            .setOkHttpClient(null);
    Ens.getEnsUrl(ensOptions, new EnsVerify() {
        @Override
        public void call(String ensInfo) {

        }
        @Override
        public void error(Throwable throwable) {

        }
    });
```




