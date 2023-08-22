package com.example.assessmentapphpl.network;

import com.example.assessmentapphpl.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public ApiInterface callRetrofit(String baseUrl) {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json; charset=UTF-8")
                            .addHeader("Accept", "application/json")
                            .build();
                    return chain.proceed(request);
                })
                .addInterceptor(BuildConfig.DEBUG ? DefaultInterceptors.getHttpBasicLoggingInterceptor() : DefaultInterceptors.getHttpNoneLoggingInterceptor());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    public static class DefaultInterceptors {

        public static HttpLoggingInterceptor getHttpNoneLoggingInterceptor() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            return interceptor;
        }

        public static HttpLoggingInterceptor getHttpBasicLoggingInterceptor() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            return interceptor;
        }
    }
}
