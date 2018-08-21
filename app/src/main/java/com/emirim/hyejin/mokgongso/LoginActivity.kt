package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*

class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null

    private lateinit var gso: GoogleSignInOptions
    private val TAG: String = "LoginActivity"
    private val RC_SIGN_IN: Int = 9001 // ?

    private var mGoogleSignInClent: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val googleSignInBtn: SignInButton = findViewById(R.id.googleSignInBtn)
        val googleSignInIcon: ImageView = findViewById(R.id.googleSignInIcon)

        var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClent = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()

        googleSignInBtn.setOnClickListener {
            signIn()
        }

        googleSignInIcon.setOnClickListener{
            googleSignInBtn.performClick()
        }

        googleSignInIcon.visibility = View.INVISIBLE
    }

    override fun onStart() {
        super.onStart()

        // 사용자가 로그인 되어 있는 지 확인
        var currentUser: FirebaseUser? = mAuth!!.currentUser
        updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 서버의 클라이언트 ID를 requestIdToken 메소드에 전달
        if(requestCode == RC_SIGN_IN) {
            var task = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            if(task.isSuccess) {
                val account = task.signInAccount
                firebaseAuthWithGoogle(account!!)
            } else {
                updateUI(null)
                Toast.makeText(applicationContext, "SignIn: failed!",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 사용자가 정상적으로 로그인 한 후, Firebase에 인증
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id)

        // showProgressDialog()

        var credential: AuthCredential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful) {
                        // 로그인 성공
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        updateUI(null)
                    }
                }
    }

    private fun signIn() {
        var signInIntent: Intent = mGoogleSignInClent!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null) {
            var intent: Intent = Intent(this, LogoutActivity::class.java)
            startActivity(intent)
        }
    }
}