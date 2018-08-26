package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.auth.ErrorCode
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestMe()
    }

    // 유저의 정보를 받아오는 함수
    fun requestMe() {
        UserManagement.requestMe(object: MeResponseCallback() {
            override fun onFailure(errorResult: ErrorResult?) {
                val message = "failed to get user info. msg = $errorResult"
                Log.d(TAG, message)

                val result: ErrorCode = ErrorCode.valueOf(errorResult!!.errorCode)
                if(result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish()
                } else {
                    redirectLoginActivity()
                }
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                redirectLoginActivity()
            }

            override fun onNotSignedUp() {
                showSignUp()
            }

            override fun onSuccess(result: UserProfile?) {
                redirectMainActivity()
            }
        });
    }

    fun showSignUp() {
        redirectLoginActivity()
    }

    private fun redirectLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun redirectMainActivity() {
        startActivity(Intent(this, MandalartActivity::class.java))
        finish()
    }
}
