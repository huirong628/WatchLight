package com.qihoo360.antilostwatch.light.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public abstract class BaseResponse {
    @Expose
    @SerializedName("retcode")
    protected int retCode;

    @Expose
    @SerializedName("errcode")
    protected int errCode;
}
