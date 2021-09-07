package com.cavss.ralo.ui.customs.popup

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cavss.ralo.R
import com.cavss.ralo.databinding.PopupNotifyBinding

class PopupNotify {
    private var getContext : Context? = null
    fun setContext(context : Context){ this.getContext = context }

    private var getPosition : View? = null
    fun setPlace(where : View){ this.getPosition = where }

    private var getTitle : String? = null
    fun setPopupTitle(title : String){ this.getTitle = title }

    private var setRecyclerView : RecyclerView? = null
    fun getRecyclerView() : RecyclerView { return setRecyclerView!! }

    private var popupWindow : PopupWindow? = null
    private lateinit var binding : PopupNotifyBinding
    fun showPopupView() : PopupWindow {
        try{
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext), R.layout.popup_notify, null, false)
            popupWindow = PopupWindow(binding.root, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow?.apply {
                isOutsideTouchable = true
                isFocusable = true
                elevation = 10.0f
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                showAtLocation(getPosition, Gravity.CENTER, 0, 0)
                update()
            }

            binding.run{
                title = getTitle
                if (setRecyclerView == null) setRecyclerView = recyclerview
                popupDismiss.setOnClickListener { dismiss() }
            }

        }catch(e : Exception){
            Log.e("mException","에러발생 : PopupNotify, showPopupView  // Exception : ${e.message}")
        }finally{
            return popupWindow!!
        }
    }



    fun dismiss(){
        if(popupWindow!!.isShowing){
            popupWindow?.dismiss()
            popupWindow = null
            getPosition = null
            getTitle = null
            getContext = null
            setRecyclerView = null
            if (popupWindow == null){
                Log.e("mException", "PopupNotify 사라짐")
            }
        }
    }
}