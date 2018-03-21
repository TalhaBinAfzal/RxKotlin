package com.nafsol.talhasedev.rxkotlin.Service;

import com.nafsol.talhasedev.rxkotlin.Model.GitUserDataModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by TalhaSeDev on 02/03/2018.
 */

public interface GitHubService {
    @GET("users/{user}")
    Observable<GitUserDataModel> getGitUserData(@Path("user") String userName);
}
