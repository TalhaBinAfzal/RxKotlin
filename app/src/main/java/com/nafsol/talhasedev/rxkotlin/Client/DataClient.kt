package com.nafsol.talhasedev.rxkotlin.Client

import com.google.gson.Gson
import com.nafsol.talhasedev.rxkotlin.Model.CompanyDataModel
import com.nafsol.talhasedev.rxkotlin.Model.ProductDataModel
import com.nafsol.talhasedev.rxkotlin.Service.DataService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by TalhaSeDev on 03/03/2018.
 */
class DataClient {
    companion object {
        lateinit var retrofit: Retrofit
        lateinit var serviceinstance: DataService

        var bseUrl = "http://13.89.191.89/sameelenterprises/salesapi/fsd/Service.svc/"
        var productUrl = "sync/get/product"
    }

    init {
        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(bseUrl)
                .build()
        serviceinstance = retrofit.create(DataService::class.java)
    }

    fun getProduct(): Observable<List<ProductDataModel>> {
        return serviceinstance.products
    }

    fun getCompany(): Observable<List<CompanyDataModel>> {
        return serviceinstance.company
    }

}