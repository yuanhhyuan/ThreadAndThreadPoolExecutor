package com.hy.mylib.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @version V1.0
 * @Package com.hy.mylib.retrofit2
 * @Description: ${todo}
 * @author: huangyuan
 * @date: 2017/11/16 17:09
 * @Copyright: www.***.com Inc. All rights reserved.
 */
public interface ApiService {
    @GET("/telematics/v3/weather")
    Call<POWeather> getWeather (@Query("location") String location, @Query("output") String ouput, @Query("ak") String ak);
}
