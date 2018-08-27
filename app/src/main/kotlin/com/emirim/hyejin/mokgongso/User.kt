package com.emirim.hyejin.mokgongso

import com.emirim.hyejin.mokgongso.model.Duplicatechk
import com.emirim.hyejin.mokgongso.model.Signin
import com.emirim.hyejin.mokgongso.model.Signup

class User {
    companion object {
        lateinit var duplicatechk: Duplicatechk
        lateinit var signUp: Signup
        lateinit var signIn: Signin
    }
}