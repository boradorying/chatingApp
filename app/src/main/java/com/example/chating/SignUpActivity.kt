package com.example.chating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chating.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인증초기화
        auth = Firebase.auth
        //db 초기화
        database = Firebase.database.reference

        binding.signUpBtn.setOnClickListener {
            val email = binding.emailEditArea.text.toString().trim()
            val password = binding.passEditArea.text.toString().trim()
            val name = binding.nameEditArea.text.toString().trim()

            signUp(email, password, name)
        }

    }

    private fun signUp(email: String, password: String, name: String) {
        if (email.isEmpty() && password.isEmpty() && name.isEmpty()) {
            Toast.makeText(this, "입력되지 않은 정보가 있습니당당당", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_LONG).show()

                    newUser(email,name, auth.currentUser?.uid!!)
                } else {
                    Toast.makeText(this, "올바른 이메일 형식 또는 비밀번호는 6자리 이상 입력하세요", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }
    fun newUser(email: String, name: String, uid: String) {

        database.child("users").child(uid).setValue(User(name,email,uid))
    }
}
