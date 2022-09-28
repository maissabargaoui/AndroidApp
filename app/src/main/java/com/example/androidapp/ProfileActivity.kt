package com.example.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private lateinit var btnlogout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnlogout = findViewById(R.id.btnlogout)
        btnlogout.setOnClickListener{
            Firebase.auth.signOut()
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "LouggedOut Successfully!", Toast.LENGTH_LONG).show()
        }
    }
}