package com.yylykym.fragmenttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class LeftFragment: Fragment() {

    // 定义接口
    interface OnButtonClickListener {
        fun onButtonClick()
    }

    private var buttonClickListener: OnButtonClickListener? = null

    // 设置接口实例
    fun setOnButtonClickListener(listener: OnButtonClickListener?) {
        buttonClickListener = listener
    }
    // 在按钮点击时调用接口方法
    private fun onButtonClicked() {
        buttonClickListener?.onButtonClick()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.left_fragment, container, false)
        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            onButtonClicked()
        }
        return view
    }
}