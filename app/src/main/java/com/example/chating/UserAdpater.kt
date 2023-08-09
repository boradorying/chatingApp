package com.example.chating
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdpater (val context : Context, val userList : ArrayList<User>) : RecyclerView.Adapter<UserAdpater.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdpater.ViewHolder {
//화면설정
        val view  = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return ViewHolder(view)
                          }

    override fun onBindViewHolder(holder: UserAdpater.ViewHolder, position: Int) {
        var currentUser = userList[position]
        holder.user_layout.text = currentUser.name
//        holder.binditems(userList[position])
        //아이템클릭이벤트
        holder.itemView.setOnClickListener{

            val intent =  Intent(context,ChatActivity::class.java)
            //넘길데이터
            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return userList.size
    }

     class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

//        fun binditems(item: User){
            val user_layout : TextView = itemView.findViewById(R.id.userTextArea)
//            user_layout.text = item.name
//        }
    }
}






















