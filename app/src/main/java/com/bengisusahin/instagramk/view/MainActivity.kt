package com.bengisusahin.instagramk.view

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bengisusahin.instagramk.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
//    var email : String = ""
//    var password :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this@MainActivity, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun signInClicked(view: View){
        val email = binding.emailText.text.toString()
        val password = binding.passwordlText.text.toString()
        if (email.equals("") || password.equals("")){
            Toast.makeText(this,"Enter email and password !", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signUpClicked(view: View){
        val email = binding.emailText.text.toString()
        val password = binding.passwordlText.text.toString()

//        if (email.isNotEmpty() && password.isNotEmpty()){
//
//        }
        if (email.equals("") || password.equals("")){
            Toast.makeText(this, "Enter email and password!", Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                //success
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }
    }
}