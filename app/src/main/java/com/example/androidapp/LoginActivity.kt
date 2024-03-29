package com.example.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast


class LoginActivity : AppCompatActivity() {

    private lateinit var tvforget: MaterialTextView
    private lateinit var tvRedirectSignUp: MaterialTextView
    lateinit var txtEmail: TextInputEditText
    private lateinit var txtPassword: TextInputEditText
    lateinit var btnLogin: MaterialButton

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // View Binding
        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)
        tvforget= findViewById(R.id.tvforget)
        btnLogin = findViewById(R.id.btnLogin)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        //keep user logged in
        checkIfUserIsLogged()

        btnLogin.setOnClickListener {
            login()
        }

        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
        tvforget.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
    }

    private fun checkIfUserIsLogged() {
        if (auth.currentUser != null){
            startActivity(Intent(this, MainActivity :: class.java))
        }
    }

    private fun login() {
        val email = txtEmail.text.toString()
        val pass = txtPassword.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                navigate()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
    private fun navigate(){
        val mainIntent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(mainIntent)
    }

}

