package com.yylykym.uibestpractice.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yylykym.uibestpractice.R

sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view) {

     class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }

     class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }

}