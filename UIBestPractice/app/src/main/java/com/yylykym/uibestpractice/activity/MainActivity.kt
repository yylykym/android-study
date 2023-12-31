package com.yylykym.uibestpractice.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yylykym.uibestpractice.R
import com.yylykym.uibestpractice.adapter.MsgAdapter
import com.yylykym.uibestpractice.databinding.ActivityMainBinding
import com.yylykym.uibestpractice.domain.Msg

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var msgList: MutableList<Msg> = ArrayList()

    private lateinit var mainBinding: ActivityMainBinding

    private var adapter: MsgAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        mainBinding.recyclerView.layoutManager = layoutManager
        mainBinding.recyclerView.adapter = MsgAdapter(msgList)
        mainBinding.send.setOnClickListener(this)
    }


    override fun onClick(view: View) {
        when(view.id) {
            mainBinding.send.id -> {
                val text = mainBinding.inputText.text.toString()
                if (text.isNotEmpty()) {
                    val msg = Msg(text, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新RecyclerView中的显示
                    mainBinding.recyclerView.scrollToPosition(msgList.size - 1)  // 将RecyclerView定位到最后一行
                    mainBinding.inputText.setText("") // 清空输入框中的内容
                }
            }
        }
    }

    private fun initMsg() {

        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)

        msgList.apply {
            add(msg1)
            add(msg2)
            add(msg3)
        }


    }


}