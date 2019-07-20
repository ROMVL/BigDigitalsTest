package com.romanik.bigdigitalstest.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.romanik.bigdigitalstest.core.viewmodel.BaseViewModel
import com.romanik.bigdigitalstest.databinding.FragmentAddPhotoBinding

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel
    abstract val layout: Int
    lateinit var dataBinding: ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layout, container, false)
        dataBinding.lifecycleOwner = this@BaseFragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            error.observe(this@BaseFragment, Observer { notify(it) })
        }
    }

    fun notify(message: String) =
        Snackbar.make(dataBinding.root, message, Snackbar.LENGTH_LONG).show()

}