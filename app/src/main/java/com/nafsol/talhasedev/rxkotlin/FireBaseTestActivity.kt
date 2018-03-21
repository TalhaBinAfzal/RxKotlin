package com.nafsol.talhasedev.rxkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.database.*
import com.nafsol.talhasedev.rxkotlin.Model.User

import kotlinx.android.synthetic.main.activity_fire_base_test.*
import kotlinx.android.synthetic.main.content_fire_base_test.*

class FireBaseTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base_test)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        initObjects()
        setbuttonCLick()
    }

    companion object {
        private var mDataBase: DatabaseReference? = null
        private var mMessageReference: DatabaseReference? = null
    }

    fun initObjects() {
        mDataBase = FirebaseDatabase.getInstance().getReference()
        mMessageReference = FirebaseDatabase.getInstance().getReference()
    }

    fun setbuttonCLick() {
        buttonOperate.setOnClickListener({
            readDb()
            //writeInDb()
        })
    }

    fun writeInDb() {
        // mDataBase!!.child("title").setValue("KotlinSampleData")
        var user = User("Qasim", "qasimgmailcom")

        mDataBase!!.child("UserData").child(user.Email).setValue(user)
    }

    fun readDb() {
        var ref = mDataBase!!.child("UserData")
        var query = ref.child("qasimgmailcom")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (item in p0!!.getChildren()) {
                    var currntItem = item.getValue();
                    var user = currntItem
//https://stackoverflow.com/questions/39800547/read-data-from-firebase-database
                }
            }
        })
    }

}
