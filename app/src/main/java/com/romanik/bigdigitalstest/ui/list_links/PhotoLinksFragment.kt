package com.romanik.bigdigitalstest.ui.list_links

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.romanik.bigdigitalstest.R

class PhotoLinksFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoLinksFragment()
    }

    private lateinit var viewModel: PhotoLinksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_links_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhotoLinksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
