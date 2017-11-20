package com.example.gentlefolks.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiGetData {

	private final String BASE_URL = "https://gentlefolks-b1933.firebaseio.com/";

	private Retrofit retrofit;

	public ApiGetData() {
		retrofit = new Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(new OkHttpClient.Builder()
				.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
				.build())
			.addConverterFactory(JacksonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.build();
	}

	public Api getApi() {
		return retrofit.create(Api.class);
	}
}