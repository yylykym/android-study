package com.yylykym.fragmentbestpractice.activity.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yylykym.fragmentbestpractice.R
import com.yylykym.fragmentbestpractice.domain.News

class NewTitleAdapter(private var news: List<News>) :
    RecyclerView.Adapter<NewTitleAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            Toast.makeText(parent.context, "clicked once !", Toast.LENGTH_SHORT).show()
        }
        return holder
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val new = news[position]
        holder.newsTitle.text = new.title
    }

}