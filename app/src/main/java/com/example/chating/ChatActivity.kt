package com.example.chating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chating.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var receiverName : String
    private lateinit var receiverUid : String

    //바인딩 객체생성
    private lateinit var binding :ActivityChatBinding
    lateinit var auth : FirebaseAuth//인증객체
    lateinit var dataBase : DatabaseReference//db객체

    private lateinit var receiverRoom: String//받는 대화방
    private lateinit var sendRoom: String//보낸대화ㅣ방

    private lateinit var messageList : ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //메시지리스트초기화
        messageList = ArrayList()

        //어댑터선언후 초기화
        val messageAdapter : MessageAdapter = MessageAdapter(this ,messageList)

        binding.chatRV.layoutManager = LinearLayoutManager(this)
        binding.chatRV.adapter = messageAdapter



        //넘어온 데이터 변수에 담기
        receiverName = intent.getStringExtra("name").toString()
        receiverUid = intent.getStringExtra("uid").toString()

        auth = FirebaseAuth.getInstance()
        dataBase = FirebaseDatabase.getInstance().reference

        //접속자  uid
        val senderUid =  auth.currentUser?.uid
        //보낸이방
        sendRoom = receiverUid + senderUid
        //받는이
        receiverRoom = senderUid + receiverUid


        //액션반에 상대방 이름보여주기

        supportActionBar?.title = receiverName

        //메시지전송버튼 이벤트

        binding.sendBtn.setOnClickListener {
            val message = binding.messageEdit.text.toString()
            val messageObject = Message(message, senderUid)

            //데이터저장

            dataBase.child("chats").child(sendRoom).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    //저장 성공하면
                    dataBase.child("chats").child(receiverRoom).child("messages").push()
                        .setValue(messageObject)
                }

            binding.messageEdit.setText("")
        }

        //메시지가져오기 어댑터에서~
        dataBase.child("chats").child(sendRoom).child("messages")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (post in snapshot.children){
                        val message =post.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    //적용
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            } )
    }
}