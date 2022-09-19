package com.example.androidapp
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView

//In the Registration activity i will create a FirebaseAuth object,
// and using it i will call the createUserWithEmailAndPassword(email, pass) function.
//And check using addOnCompleteListener() function, if the response is successful then will display a Toast.
class RegisterActivity : AppCompatActivity() {

    lateinit var txtEmail: TextInputEditText
    lateinit var txtLayoutEmail: TextInputLayout
    private lateinit var txtPassword: TextInputEditText
    lateinit var txtLayoutPassword: TextInputLayout
    lateinit var txtPassword2: TextInputEditText
    lateinit var txtLayoutPassword2: TextInputLayout
    private lateinit var btnRegister: MaterialButton
    lateinit var tvRedirectLogin: MaterialTextView

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // View Bindings
        txtEmail = findViewById(R.id.txtEmail)
        txtLayoutEmail = findViewById(R.id.txtLayoutEmail)
        txtPassword = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)
        txtPassword2 = findViewById(R.id.txtPassword2)
        txtLayoutPassword2 = findViewById(R.id.txtLayoutPassword2)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)
        btnRegister = findViewById(R.id.btnRegister)

        // Initialising auth object
        auth = Firebase.auth

        btnRegister.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val email = txtEmail.text.toString()
        val pass = txtPassword.text.toString()
        val confirmPassword = txtPassword2.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}