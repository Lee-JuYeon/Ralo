package com.cavss.ralo.ui.customs.recyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter {

    abstract class Adapter<MODEL : Any, BIND : ViewDataBinding>(
        @LayoutRes
        private val layoutResId: Int,
        private val bindingVariableId: Int? = null,
        private val bindingClickVariableID : Int?
    ) : RecyclerView.Adapter<BaseViewHolder<BIND>>() {

        private val items : MutableList<MODEL> = mutableListOf<MODEL>()
        fun updateList(list : List<MODEL>?){
            val diffResult = DiffUtil.calculateDiff(
                BaseDifUtil<MODEL>(
                    oldList = items,
                    newList = list ?: mutableListOf()),
                false)
            diffResult.dispatchUpdatesTo(this)
            items.clear()
            items.addAll(list ?: mutableListOf())
        }

        private var iClickListener : IClickListener? = null
        fun setOnClick(clickListener : IClickListener){
            this.iClickListener = clickListener
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : BaseViewHolder<BIND>(
                layoutResId = layoutResId,
                parent = parent,
                bindingVariableId = bindingVariableId,
                bindingClickVariableID = bindingClickVariableID,
                onClick = iClickListener
            ) {}

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: BaseViewHolder<BIND>, position: Int) {
            holder.onBindViewHolder(items[position])
        }

        override fun onViewRecycled(holder: BaseViewHolder<BIND>) {
            super.onViewRecycled(holder)
            holder.onBindViewHolder(null)
        }
    }
}

/*
activity {
    adapter.updateList()
    adapter.setOnclick()


    adpater {
        updateList() bone
        setOnCLick() bone

        viewholder{
            custom click() bone
            model(image, title)
        }

    }

}
 */