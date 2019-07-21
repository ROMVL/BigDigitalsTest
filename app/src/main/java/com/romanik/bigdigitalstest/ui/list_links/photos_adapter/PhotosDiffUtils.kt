package com.romanik.bigdigitalstest.ui.list_links.photos_adapter

import androidx.recyclerview.widget.DiffUtil
import com.romanik.bigdigitalstest.domain.model.Photo

class PhotosDiffUtils(
    private val oldData: List<Photo>,
    private val newData: List<Photo>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        return oldItem.link == newItem.link && oldItem.date == newItem.date && oldItem.status == newItem.status
    }
}