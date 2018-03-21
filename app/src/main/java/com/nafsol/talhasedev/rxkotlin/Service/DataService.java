package com.nafsol.talhasedev.rxkotlin.Service;

import com.nafsol.talhasedev.rxkotlin.Model.CompanyDataModel;
import com.nafsol.talhasedev.rxkotlin.Model.ProductDataModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;

/**
 * Created by TalhaSeDev on 03/03/2018.
 */

public interface DataService {
    @GET("sync/get/product")
    Observable<List<ProductDataModel>> getProducts();

    @GET("sync/get/company")
    Observable<List<CompanyDataModel>> getCompany();
}
