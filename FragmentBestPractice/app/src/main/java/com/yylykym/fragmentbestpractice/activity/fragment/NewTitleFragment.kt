package com.yylykym.fragmentbestpractice.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yylykym.fragmentbestpractice.activity.fragment.adapter.NewTitleAdapter
import com.yylykym.fragmentbestpractice.databinding.FragmentNewTitleBinding
import com.yylykym.fragmentbestpractice.domain.News


/**
 * A simple [Fragment] subclass.
 * Use the [NewTitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTitleFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var newTitleBinding: FragmentNewTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        newTitleBinding = FragmentNewTitleBinding.inflate(inflater, container, false)
        newTitleBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        newTitleBinding.recyclerView.adapter = NewTitleAdapter(getNews())
        return newTitleBinding.root
    }

    private fun getNews(): List<News> =
        ArrayList<News>().apply {
            for (i in 1..50) {
                add(
                    News(
                        "This is news title $i",
                        getRandomLengthString("This is news content $i. ")
                    )
                )
            }
        }


    private fun getRandomLengthString(content: String): String =
        StringBuilder().apply {
            repeat((1..20).random()) {
                append(content)
            }
        }.toString()


}