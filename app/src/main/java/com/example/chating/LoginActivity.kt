package com.example.chating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chating.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        binding.loginBtn.setOnClickListener {
            val email = binding.emailEditArea.text.toString()
            val password = binding.passEditArea.text.toString()


            login(email,password)
        }


        binding.signupBtn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun login(email : String ,password : String){
        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "입력되지 않은 정보가 있습니당당당", Toast.LENGTH_LONG).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,HomeActivity::class.java )
                    startActivity(intent)
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_LONG).show()
                    finish()


                } else {
                    Toast.makeText(this,"아이디 또는 비밀번호를 확인해주세요",Toast.LENGTH_LONG).show()
                }
            }
    }
}