package com.nafsol.talhasedev.rxkotlin.Model

/**
 * Created by TalhaSeDev on 05/03/2018.
 */
class User {
    var UserName: String = ""
    var Email: String = ""

    constructor(UserName: String, Email: String) {
        this.UserName = UserName
        this.Email = Email
    }
}