package com.cavss.ralo.ui.customs.recyclerview

import androidx.recyclerview.widget.DiffUtil

class BaseDifUtil<MODEL : Any>(
    private val oldList: MutableList<MODEL>,
    private val newList: List<MODEL>
) : DiffUtil.Callback() {
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
}