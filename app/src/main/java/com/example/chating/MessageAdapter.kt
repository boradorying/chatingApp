package com.example.chating

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter (private val context : Context, private val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        //q보낸쪽
    class SendViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val sendMessage : TextView = itemView.findViewById(R.id.send_message_text)
    }
        //받는쪽
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val receiveMessage : TextView = itemView.findViewById(R.id.receive_message_text)
    }

    private val receive = 1//받는타입
    private val send  = 2//보내는타입

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  if (viewType == 1 ){
            //받는화면
            val view : View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            ReceiveViewHolder(view)
        }else{
            //보내는화면
            val view : View = LayoutInflater.from(context).inflate(R.layout.semd,parent,false)
            SendViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return  messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //현재메시지
        val currentMessage =messageList[position]
        //보내는 데이터
        if (holder.javaClass == SendViewHolder::class.java){
            val viewHolder = holder as SendViewHolder
            viewHolder.sendMessage.text = currentMessage.message
        }else{//받는 데이터
            val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message

        }

    }

    override fun getItemViewType(position: Int): Int {
        //메시지값
        val currentMessage = messageList[position]

        return if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sendId)){
            send
        }else{
            receive
        }
    }
}