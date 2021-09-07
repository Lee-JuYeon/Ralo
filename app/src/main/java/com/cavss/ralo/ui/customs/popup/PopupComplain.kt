package com.cavss.ralo.ui.customs.popup

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import com.cavss.ralo.R
import com.cavss.ralo.databinding.PopupComplainBinding

class PopupComplain {
    private var getContext : Context? = null
    fun setContext(context : Context){ this.getContext = context }

    private var getPosition : View? = null
    fun setPlace(where : View){ this.getPosition = where }

    private var getTitle : String? = null
    fun setPopupTitle(title : String){ this.getTitle = title }

    private var getHint : String? = null
    fun setHint(get : String){ this.getHint = get }

    fun getEnter(
        enterButton : (ImageView, EditText) -> Unit
    ){
        enterButton(binding.enter, binding.edittext)
    }

    private var popupWindow : PopupWindow? = null
    private lateinit var binding : PopupComplainBinding
    fun showPopupView() : PopupWindow {
        try{
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext), R.layout.popup_complain, null, false)
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
                popupDismiss.setOnClickListener { dismiss() }
            }

        }catch(e : Exception){
            Log.e("mException","에러발생 : PopupComplain, showPopupView  // Exception : ${e.message}")
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
            getHint = null
            getContext = null
            if (popupWindow == null){
                Log.e("mException", "PopupComplain 사라짐")
            }
        }
    }
}