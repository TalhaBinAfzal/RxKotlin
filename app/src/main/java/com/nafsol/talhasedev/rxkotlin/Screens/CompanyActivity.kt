package com.nafsol.talhasedev.rxkotlin.Screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.nafsol.talhasedev.rxkotlin.Client.DataClient
import com.nafsol.talhasedev.rxkotlin.MainActivity
import com.nafsol.talhasedev.rxkotlin.Model.CompanyDataModel
import com.nafsol.talhasedev.rxkotlin.Model.ProductDataModel
import com.nafsol.talhasedev.rxkotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)
        getCompanies()
    }

    fun getCompanies() {
        progressBar.visibility = View.VISIBLE
        setListView("Companies requested")
        var company = DataClient()
                .getCompany()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<CompanyDataModel>>() {
                    override fun onNext(t: List<CompanyDataModel>?) {
                        progressBar.visibility = View.GONE
                        for (item in t!!) {
                            setListView(item.CompanyId)
                        }
                    }

                    override fun onError(e: Throwable?) {
                    }

                    override fun onCompleted() {
                    }


                })
    }

    fun setListView(message: String) {
        MainActivity.stringList.add(message)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.stringList)
        list_view_repos.adapter = adapter
    }
}
