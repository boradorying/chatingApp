package com.example.chating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chating.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
   private lateinit var binding : ActivityHomeBinding
    private lateinit var adpater: UserAdpater
    private lateinit var auth : FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var  userList : ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인증초기화
        auth = Firebase.auth
        //db 초기화
        database = Firebase.database.reference
        //리스트초기호ㅓㅏ
        userList = ArrayList()

        adpater = UserAdpater(this,userList)

        binding.userRV.layoutManager = LinearLayoutManager(this)
        binding.userRV.adapter = adpater


        //사용자 정보 가져오기

        database.child("users").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(post in snapshot.children){

                    //본인을 제외한 유저정보
                    var currentUser = post.getValue(User::class.java)

                    if (auth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }
                }
                adpater.notifyDataSetChanged()
                binding.userRV.invalidate()

                Log.d("user",userList.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //홈액티비티에서 menu를 보여주기위해서 함수생성
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //로그아웃이 되는 기능과 로그인화면으로 이동하는 화면
        if (item.itemId == R.id.log_out){
            auth.signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
            return  true
        }
        return true
    }
}