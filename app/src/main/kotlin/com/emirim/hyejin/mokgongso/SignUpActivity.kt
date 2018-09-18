package com.emirim.hyejin.mokgongso

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.emirim.hyejin.mokgongso.api.APIRequestManager
import com.emirim.hyejin.mokgongso.model.Duplicatechk
import com.emirim.hyejin.mokgongso.model.Message
import com.emirim.hyejin.mokgongso.model.Signup
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    companion object {
        var joinBoolean = false
        var emailBoolean = false

        lateinit var matcher: Matcher
    }

    val EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        leftArrow.setOnClickListener {
            finish()
        }

        signUpBtn.isEnabled = false

        nameEdt.addTextChangedListener(addListenerOnTextChange(this, nameEdt))
        emailEdt.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus && emailEdt.text.toString().trim().isNotEmpty()) {
                // 유효성 검사
                matcher = EMAIL_ADDRESS_REGEX.matcher(emailEdt.text.toString())

                if(matcher.find()) {
                    User.duplicatechk = Duplicatechk(emailEdt.text.toString())

                    // 아이디 중복 확인
                    var call: Call<Message> = APIRequestManager.getInstance().requestServer().duplicatechk(User.duplicatechk)
                    call.enqueue(object : Callback<Message> {
                        override fun onResponse(call: Call<Message>, response: Response<Message>) {
                            when (response.code()) {
                                200 -> {
                                    Log.d("duplicatechk", "200")
                                    emailCheck.setImageResource(R.mipmap.shape_3)
                                    emailEdt.setBackgroundResource(R.drawable.solid)
                                    emailBoolean = true
                                }
                                409 -> {
                                    Log.d("duplicatechk", "409")
                                    emailEdt.setBackgroundResource(R.drawable.dotted)
                                    emailCheck.setImageResource(R.mipmap.shape_4)
                                    emailBoolean = false
                                }
                            }
                        }

                        override fun onFailure(call: Call<Message>, t: Throwable) {
                            Log.e("SignUpActivity", "에러: " + t.message)
                            t.printStackTrace()
                        }
                    })
                } else {
                    emailEdt.setBackgroundResource(R.drawable.dotted)
                    emailCheck.setImageResource(R.mipmap.shape_4)
                    emailBoolean = false
                }
            }
        }

        pwEdt.addTextChangedListener(addListenerOnTextChange(this, pwEdt))

        pwConfirmEdt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s != null && s.trim().isNotEmpty() && pwConfirmEdt != null && pwConfirmEdt.text.toString().equals(pwEdt.text.toString())) {
                    pwConfirmEdt.setBackgroundResource(R.drawable.solid)
                    joinBoolean = true
                    pwConfirmCheck.setImageResource(R.mipmap.shape_3)

                    // 회원가입 버튼 활성화
                    signUpBtn.setBackgroundResource(R.drawable.loginbutton)
                    signUpBtn.isEnabled = true
                } else {
                    pwConfirmEdt.setBackgroundResource(R.drawable.dotted)
                    joinBoolean = false
                    pwConfirmCheck.setImageResource(R.mipmap.shape_4)
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        signUpBtn.setOnClickListener {
            // 회원가입 버튼 활성화
            if(!(nameEdt.text.toString().trim().isNotEmpty() && emailBoolean && pwEdt.text.toString().trim().isNotEmpty() && joinBoolean)) {
                if(!nameEdt.text.toString().trim().isNotEmpty()) nameEdt.requestFocus()
                else if(emailBoolean) emailEdt.requestFocus()
                else if(!pwEdt.text.toString().trim().isNotEmpty()) pwEdt.requestFocus()
                else pwConfirmEdt.requestFocus()
            } else {
                User.signUp = Signup(nameEdt.text.toString(), emailEdt.text.toString(), pwEdt.text.toString())

                // 회원가입
                var call: Call<Message> = APIRequestManager.getInstance().requestServer().signup(User.signUp)

                call.enqueue(object: Callback<Message> {
                    override fun onResponse(call: Call<Message>, response: Response<Message>) {
                        when(response.code()) {
                            200 -> {
                                Log.d("signup", "200")
                                finish()
                            }
                            400 -> {
                                Log.d("signup", "400")
                            }
                            409 -> {
                                Log.d("signup", "409")
                                emailEdt.requestFocus()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Message>, t: Throwable) {
                        Log.e("SignUpActivity", "에러: " + t.message)
                        t.printStackTrace()
                    }
                })
            }
        }
    }

    class addListenerOnTextChange : TextWatcher {
        var mContext: Context? = null
        var mEditText: EditText? = null

        constructor(context: Context, editText: EditText) {
            this.mContext = context
            this.mEditText = editText
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Text 변경 전 문자열 출력
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Text가 변경 중일 때 호출되는 함수
            if(s == null || s.trim().length < 1) {
                mEditText!!.setBackgroundResource(R.drawable.dotted)
            } else {
                mEditText!!.setBackgroundResource(R.drawable.solid)
            }
        }
        override fun afterTextChanged(s: Editable?) {
            // Text 변경되었을 때 호출되는 함수
        }
    }
}