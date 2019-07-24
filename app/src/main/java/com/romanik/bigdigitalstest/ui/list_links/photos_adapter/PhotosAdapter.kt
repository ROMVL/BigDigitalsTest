package com.romanik.bigdigitalstest.ui.list_links.photos_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.romanik.bigdigitalstest.BR
import com.romanik.bigdigitalstest.core.recyclerview.DataBindingViewHolder
import com.romanik.bigdigitalstest.databinding.ItemPhotoRowBinding
import com.romanik.bigdigitalstest.domain.model.Photo

class PhotosAdapter(
    private val listener: OnClickPhotoListener
) : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    private var photos = listOf<Photo>()

    fun setData(newPhotos: List<Photo>) {
        val photosDiffUtils = PhotosDiffUtils(photos, newPhotos)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(photosDiffUtils)
        photos = newPhotos
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) = holder.onBind(photos[position])

    inner class PhotoViewHolder(private val viewDataBinding: ViewDataBinding) :
        DataBindingViewHolder<Photo>(viewDataBinding) {

        override fun onBind(t: Photo) {
            viewDataBinding.setVariable(BR.photo, t)
            viewDataBinding.root.setOnClickListener { listener.onClick(t) }
        }

    }

    interface OnClickPhotoListener {
        fun onClick(photo: Photo)
    }

}


