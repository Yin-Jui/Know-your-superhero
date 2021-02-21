package com.example.knowyoursuperhero

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login2.*


class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignIn: GoogleSignInClient
    private val GOOGLE_SIGNIN = 200
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignIn = GoogleSignIn.getClient(this, gso)
        google_signin.setOnClickListener {

            startActivityForResult(googleSignIn.signInIntent, GOOGLE_SIGNIN);
        }
        Signup.setOnClickListener {
            signUp()
        }
        login2.setOnClickListener{
            login()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SIGNIN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
            FirebaseAuth.getInstance()
                    .signInWithCredential(credential)
                    .addOnCompleteListener{
                        task->
                        if(task.isSuccessful){
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        else{
                            Snackbar.make(main_signin, "Authentication fail", Snackbar.LENGTH_LONG).show()
                        }
                    }
        }
    }



    private fun login() {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.text.toString(), password2.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Log In")
                                .setPositiveButton("OK") { dialog, which ->
                                    setResult(Activity.RESULT_OK)
                                    finish()
                                }.show()
                    } else {
                        AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Log In")
                                .setMessage(task.exception?.message)
                                .setPositiveButton("OK", null)
                                .show()
                    }
                }
    }

    private fun signUp() {
        val sEmail = email.text.toString();
        val sPassword = password2.text.toString();
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener { //async call
                    task ->
                    if (task.isSuccessful) {
                        AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Sign up")
                                .setMessage("Account created")
                                .setPositiveButton("OK") { dialog, which ->
                                    setResult(RESULT_OK)
                                    finish()
                                }.show()
                    } else {
                        AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Sign up")
                                .setMessage(task.exception?.message)
                                .setPositiveButton("OK", null)
                                .show()
                    }
                }
    }
}