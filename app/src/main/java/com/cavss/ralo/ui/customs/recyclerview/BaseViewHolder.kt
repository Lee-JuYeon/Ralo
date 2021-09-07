package com.cavss.ralo.ui.customs.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup,
    private val bindingVariableId: Int?,
    private val bindingClickVariableID : Int?,
    private var onClick : IClickListener?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
) {
    protected val binding: B = DataBindingUtil.bind(itemView)!!

    fun onBindViewHolder(model: Any?) {
        try {
            bindingClickVariableID?.let {
                binding.setVariable(it, onClick)
            }
            bindingVariableId?.let {
                binding.setVariable(it, model)
            }
            binding.executePendingBindings()
        } catch (e: Exception) {
            Log.e("mException", "BaseViewHolder, onBindViewHolder // Exception : ${e.message}")
        }
    }
}

/*


abstract class BaseViewHolder<in Model : Any?>(
    private val BR : Int,
    private val holderBinding : ViewDataBinding,
) : RecyclerView.ViewHolder(holderBinding.root) {


    fun onBindViewHolder(model : Any?){
        try {
//            onViewCreated(model as? Model?)
            holderBinding.apply {
                setVariable(BR, model)
                executePendingBindings()
            }
        }catch (e:Exception){
            Log.e("mException", "BaseViewHolder, onBindViewHolder // Exception : ${e.message}")
        }
    }

    abstract fun onViewCreated(model : Model?)
}

abstract class BaseViewHolder<in ITEM : Any>(
        val context: Context,
        @LayoutRes layoutRes: Int,
        parent: ViewGroup?)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutRes, parent, false)) {
    fun onBindViewHolder(item: Any?) {
        try {
            onViewCreated(item as? ITEM?)
        } catch (e: Exception) {
            itemView.visibility = View.GONE
        }
    }
    abstract fun onViewCreated(item: ITEM?)
}
https://thdev.tech/android/2018/01/31/Recycler-Adapter-Distinguish/
 */