package com.cavss.ralo.ui.customs.popup

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import com.cavss.ralo.R
import com.cavss.ralo.databinding.PopupDeveloperBinding
import com.cavss.ralo.model.DeveloperModel

class PopupDeveloper {
    private var getContext : Context? = null
    fun setContext(context : Context){ this.getContext = context }

    private var getPosition : View? = null
    fun setPlace(where : View){ this.getPosition = where }

    private var getTitle : String? = null
    fun setPopupTitle(title : String){ this.getTitle = title }

    private var getDeveloperModel : DeveloperModel? = null
    fun setContent(developerModel : DeveloperModel){
        this.getDeveloperModel = developerModel
    }

    private var popupWindow : PopupWindow? = null
    private lateinit var binding : PopupDeveloperBinding
    fun showPopupView() : PopupWindow {
        try{
            val options = BitmapFactory.Options()
            options.inSampleSize = 1
            val image = BitmapFactory.decodeResource(getContext?.resources, R.drawable.image_vendetta, options)

            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext), R.layout.popup_developer, null, false)
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
                name.text = getDeveloperModel?.name
                age.text = getDeveloperModel?.age
                description.text = getDeveloperModel?.description
//                selfie.setImageDrawable(getSelfie)
                popupDismiss.setOnClickListener { dismiss() }
            }

        }catch(e : Exception){
            Log.e("mException","에러발생 : PopupDeveloper, showPopupView  // Exception : ${e.message}")
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
            getDeveloperModel = null
            if (popupWindow == null){
                Log.e("mException", "PopupDeveloper 사라짐")
            }
        }
    }
}