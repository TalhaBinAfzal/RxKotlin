package com.nafsol.talhasedev.rxkotlin.Screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.nafsol.talhasedev.rxkotlin.Client.DataClient
import com.nafsol.talhasedev.rxkotlin.MainActivity
import com.nafsol.talhasedev.rxkotlin.Model.ProductDataModel
import com.nafsol.talhasedev.rxkotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        getProducts()
    }

    fun getProducts() {
        progressBar.visibility = View.VISIBLE
        setListView("products requesteds")
        var products = DataClient()
                .getProduct().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<ProductDataModel>>() {
                    override fun onNext(t: List<ProductDataModel>?) {
                        var products = t
                        progressBar.visibility = View.GONE
                        for (item in t!!) {
                            setListView(item.ProductName)
                        }
                        setListView("Products downloaded")
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                    }
                })
    }

    fun setListView(message: String) {
        MainActivity.stringList.add(message)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.stringList)
        list_view_repos.adapter = adapter
    }
}
