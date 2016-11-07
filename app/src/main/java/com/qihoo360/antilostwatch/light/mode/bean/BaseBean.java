package com.qihoo360.antilostwatch.light.mode.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 */

public abstract class BaseBean {
    @SerializedName("retcode")
    protected int retCode;

    @SerializedName("errcode")
    protected int errCode;

    public int getRetCode() {
        return retCode;
    }

    public int getErrCode() {
        return errCode;
    }

}
