package com.example.gentlefolks.api;

import com.example.gentlefolks.presentation.mainScreen.opinionslist.OpinionMain;
import com.example.gentlefolks.presentation.opinionsScreen.Opinion;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

	@GET("opinioins_list.json")
	Observable<List<OpinionMain>> opinionsList();

	@GET("opinions/{opinion_id}.json")
	Observable<List<Opinion>> opinionList(@Path("opinion_id") String opinion_id);

}