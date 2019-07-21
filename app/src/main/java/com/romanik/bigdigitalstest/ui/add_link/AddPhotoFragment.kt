package com.romanik.bigdigitalstest.ui.add_link

import android.os.Bundle
import android.view.View

import com.romanik.bigdigitalstest.R
import com.romanik.bigdigitalstest.core.fragment.BaseFragment
import com.romanik.bigdigitalstest.databinding.FragmentAddPhotoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPhotoFragment : BaseFragment() {

    companion object {
        fun newInstance() = AddPhotoFragment()
    }

    override val viewModel: AddPhotoViewModel by viewModel()
    override val layout: Int = R.layout.fragment_add_photo
    //private val fragmentDataBinding = dataBinding as FragmentAddPhotoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dataBinding as FragmentAddPhotoBinding).viewModel = viewModel
    }

}
