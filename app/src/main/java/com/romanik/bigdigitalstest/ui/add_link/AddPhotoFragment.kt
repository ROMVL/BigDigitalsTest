package com.romanik.bigdigitalstest.ui.add_link

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer

import com.romanik.bigdigitalstest.R
import com.romanik.bigdigitalstest.core.Constants
import com.romanik.bigdigitalstest.core.fragment.BaseFragment
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
        (dataBinding as FragmentAddPhotoBinding).apply {
            this.viewModel = this@AddPhotoFragment.viewModel
            this.link = this@AddPhotoFragment.viewModel.photoLink
        }
        with(viewModel) {
            eventPhotoViewModel.observe(this@AddPhotoFragment, Observer {
                when(it.content) {
                    AddPhotoViewModel.AddPhotoEvent.OPEN_SECOND_APP -> openSecondApp()
                }
            })
        }
    }

    private fun openSecondApp() {
        viewModel.photoLink.get()?.let { link ->
            val intentSecondApp = requireContext().packageManager.getLaunchIntentForPackage(Constants.SECOND_APP_PACKAGE)
            if (intentSecondApp != null) {
                intentSecondApp.apply {
                    putExtra(Constants.LINK, link)
                    putExtra(Constants.WHERE_FROM, Constants.ADD_LINK_SCREEN)
                }
                startActivity(intentSecondApp)
            } else {
                val throwable = Throwable("Required application not installed")
                viewModel.errorHandler(throwable)
            }
        }
    }

}
