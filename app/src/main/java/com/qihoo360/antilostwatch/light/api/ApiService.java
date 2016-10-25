package com.qihoo360.antilostwatch.light.api;

import com.qihoo360.antilostwatch.light.response.GetIpInfoResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public interface ApiService {
    @GET("/request.php")
    Observable<GetIpInfoResponse> getIpInfo(@Query("ip") String ip);
}
