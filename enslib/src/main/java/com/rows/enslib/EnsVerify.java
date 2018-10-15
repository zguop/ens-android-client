package com.rows.enslib;

/**
 * auth aboom
 * date 2018/10/12
 */
public interface EnsVerify {
    void call(String dapp);
    void error(Throwable throwable);
}
