package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var btn_submit: MaterialButton
    lateinit var txtEmailfp: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        txtEmailfp= findViewById(R.id.txtEmailfp)
        btn_submit = findViewById(R.id.btn_submit)

        btn_submit.setOnClickListener {
            val email: String = txtEmailfp.text.toString().trim { it <= ' '}
        //trim remove all spaces from text except for single spaces between words

            if (email.isEmpty()){
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    "Please Enter Your Email Adress.",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Email sent successfully to reset your password!",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}











