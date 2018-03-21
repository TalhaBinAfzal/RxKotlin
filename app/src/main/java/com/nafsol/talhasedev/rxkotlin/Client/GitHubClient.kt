package com.nafsol.talhasedev.rxkotlin.Client

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nafsol.talhasedev.rxkotlin.Model.GitUserDataModel
import com.nafsol.talhasedev.rxkotlin.Service.GitHubService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.util.*

/**
 * Created by TalhaSeDev on 02/03/2018.
 */
class GitHubClient {

    companion object {
        lateinit var retrofit: Retrofit
    }

    fun getRetroFit(userName: String): Observable<GitUserDataModel>? {
        var gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .build()
        var serviceInterface = retrofit.create(GitHubService::class.java)
        return serviceInterface.getGitUserData(userName)
    }
}