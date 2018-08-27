package com.emirim.hyejin.mokgongso

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    companion object {
        var joinBoolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        leftArrow.setOnClickListener {
            finish()
        }

        signUpBtn.isEnabled = false

        nameEdt.addTextChangedListener(addListenerOnTextChange(this, nameEdt))
        emailEdt.addTextChangedListener(addListenerOnTextChange(this, emailEdt))
        pwEdt.addTextChangedListener(addListenerOnTextChange(this, pwEdt))

        pwConfirmEdt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s != null && s.trim().isNotEmpty() && pwConfirmEdt != null && pwConfirmEdt.text.toString().equals(pwEdt.text.toString())) {
                    pwConfirmEdt.setBackgroundResource(R.drawable.solid)
                    joinBoolean = true

                    // 회원가입 버튼 활성화
                    if(nameEdt.text.toString().trim().isNotEmpty() && emailEdt.text.toString().trim().isNotEmpty() && pwEdt.text.toString().trim().isNotEmpty() && joinBoolean) {
                        signUpBtn.setBackgroundResource(R.drawable.loginbutton)
                        signUpBtn.isEnabled = true
                    }
                } else {
                    pwConfirmEdt.setBackgroundResource(R.drawable.dotted)
                    joinBoolean = false
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        signUpBtn.setOnClickListener {
            // 회원가입 버튼 활성화
            if(!(nameEdt.text.toString().trim().isNotEmpty() && emailEdt.text.toString().trim().isNotEmpty() && pwEdt.text.toString().trim().isNotEmpty() && joinBoolean)) {
                if(!nameEdt.text.toString().trim().isNotEmpty()) nameEdt.requestFocus()
                else if(!emailEdt.text.toString().trim().isNotEmpty()) emailEdt.requestFocus()
                else if(!pwEdt.text.toString().trim().isNotEmpty()) pwEdt.requestFocus()
                else pwConfirmEdt.requestFocus()
            } else {

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