package com.android.akesosdkapp.util

import androidx.recyclerview.widget.DiffUtil
import com.android.akesosdkapp.model.local.entity.User

class UserDiffUtil(private val newList: ArrayList<User>? = null, private val oldList: ArrayList<User>? = null) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.id == newList?.get(newItemPosition)?.id
    }

    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition) === newList?.get(newItemPosition)
    }
}
