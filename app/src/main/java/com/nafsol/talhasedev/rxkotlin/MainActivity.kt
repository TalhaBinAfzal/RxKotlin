package com.nafsol.talhasedev.rxkotlin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.nafsol.talhasedev.rxkotlin.Client.DataClient
import com.nafsol.talhasedev.rxkotlin.Client.GitHubClient
import com.nafsol.talhasedev.rxkotlin.Model.CompanyDataModel
import com.nafsol.talhasedev.rxkotlin.Model.GitUserDataModel
import com.nafsol.talhasedev.rxkotlin.Model.ProductDataModel
import com.nafsol.talhasedev.rxkotlin.Screens.CompanyActivity
import com.nafsol.talhasedev.rxkotlin.Screens.ProductsActivity

import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTextListner()
        buttonListener()

        buttonProductClick()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        var stringList: MutableList<String> = ArrayList()
    }

    fun buttonProductClick() {
        buttonGetProducts.setOnClickListener({
            var intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        })

        buttonGetCompanies.setOnClickListener({
            var intent = Intent(this, CompanyActivity::class.java)
            startActivity(intent)
        })
    }


    fun buttonListener() {
        button_search.setOnClickListener({
            var intent = Intent(this, FireBaseTestActivity::class.java)
            startActivity(intent)

            /*progressBar.visibility = View.VISIBLE
            getProducts()
            getCompanies()*/

            /* var name = edit_text_username.text.toString()
             var subscription = GitHubClient()
                     .getRetroFit(name)
                     ?.subscribeOn(Schedulers.io())
                     ?.observeOn(AndroidSchedulers.mainThread())
                     ?.subscribe({ list ->

                         var item = list
                         Log.e("Out put", item.followersUrl)
                     });*/
        })
    }

    fun getProducts() {
        setListView("products requesteds")
        var products = DataClient()
                .getProduct().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<ProductDataModel>>() {
                    override fun onNext(t: List<ProductDataModel>?) {
                        var products = t
                        setListView("Products downloaded")
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                    }
                })
    }

    fun getCompanies() {
        setListView("Companies requested")
        var company = DataClient()
                .getCompany()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<CompanyDataModel>>() {
                    override fun onNext(t: List<CompanyDataModel>?) {
                        setListView("Companies downloaded")
                    }

                    override fun onError(e: Throwable?) {
                    }

                    override fun onCompleted() {
                    }


                })
    }

    fun setListView(message: String) {
        stringList.add(message)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList)
        list_view_repos.adapter = adapter
    }

    fun setTextListner() {
        Observable.create(Observable.OnSubscribe<String> { subscribe ->
            editTextGetText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    subscribe.onNext(s.toString())
                }
            })
        }).debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ text ->
                    //textViewResult.text = "Output : " + text
                })
    }
}
