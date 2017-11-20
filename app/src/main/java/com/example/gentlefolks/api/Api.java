package com.example.gentlefolks.api;

import com.example.gentlefolks.ui.mainScreen.OpinionMain;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

	@GET("opinioins_list.json")
	Observable<List<OpinionMain>> opinionsList();

}