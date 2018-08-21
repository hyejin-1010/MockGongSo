package com.emirim.hyejin.mokgongso

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class LogoutActivity : AppCompatActivity() {
    private val signOutBtn by lazy {
        findViewById(R.id.signOutBtn) as Button
    }

    private var mAuth: FirebaseAuth? = null

    private var mGoogleSignInClent: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClent = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()

        signOutBtn.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        mAuth!!.signOut()

        mGoogleSignInClent!!.signOut().addOnCompleteListener(this) {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}