package com.romanik.bigdigitalstest.ui.add_link

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.romanik.bigdigitalstest.R
import com.romanik.bigdigitalstest.core.fragment.BaseFragment
import com.romanik.bigdigitalstest.core.viewmodel.BaseViewModel
import com.romanik.bigdigitalstest.databinding.FragmentAddPhotoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPhotoFragment : BaseFragment() {

    companion object {
        fun newInstance() = AddPhotoFragment()
    }

    override val viewModel: AddPhotoViewModel by viewModel()
    override val layout: Int = R.layout.fragment_add_photo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dataBinding as FragmentAddPhotoBinding).viewModel = viewModel
    }

}
