package com.example.governmentappointmentapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var edtEmail:EditText
    lateinit var edtPassword:EditText
    lateinit var btnRegister:Button
    lateinit var tvLogin:TextView
    lateinit var mAuth:FirebaseAuth
    lateinit var progress:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtEmail = findViewById(R.id.mEdtEmail)
        edtPassword = findViewById(R.id.mEdtPassword)
        btnRegister = findViewById(R.id.mBtnRegister)
        tvLogin = findViewById(R.id.mTvLogin)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        btnRegister.setOnClickListener {
            var email = edtEmail.text.toString()
            var password = edtPassword.text.toString()
            var id = System.currentTimeMillis().toString()
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all inputs", Toast.LENGTH_SHORT).show()
            }else if (password.length < 6){
                Toast.makeText(this, "Password must be 6 or more characters", Toast.LENGTH_SHORT).show()
            }else{
                progress.show()
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,AppointmentActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        tvLogin.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}