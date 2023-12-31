package com.yylykym.uibestpractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yylykym.uibestpractice.R
import com.yylykym.uibestpractice.domain.Msg
import com.yylykym.uibestpractice.holder.MsgViewHolder

class MsgAdapter(private val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == Msg.TYPE_RECEIVED) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            MsgViewHolder.LeftViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            MsgViewHolder.RightViewHolder(view)
        }

    override fun getItemCount(): Int = msgList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is MsgViewHolder.LeftViewHolder -> holder.leftMsg.text = msg.content
            is MsgViewHolder.RightViewHolder -> holder.rightMsg.text = msg.content
        }
    }

    override fun getItemViewType(position: Int): Int = msgList[position].type
}