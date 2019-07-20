package com.romanik.bigdigitalstest.ui.add_link

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.romanik.bigdigitalstest.R

class AddPhotoFragment : Fragment() {

    companion object {
        fun newInstance() = AddPhotoFragment()
    }

    private lateinit var viewModel: AddPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddPhotoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
