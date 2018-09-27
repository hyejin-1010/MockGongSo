package com.emirim.hyejin.mokgongso

import com.emirim.hyejin.mokgongso.model.*

class User {
    companion object {
        lateinit var duplicatechk: Duplicatechk
        lateinit var signUp: Signup
        lateinit var signIn: Signin
        lateinit var fb: Fb
        lateinit var delUser: DelUser

        var startDay = ""
        var first = false
    }
}