package com.romanik.bigdigitalstest.ui.list_links

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.romanik.bigdigitalstest.R
import com.romanik.bigdigitalstest.core.Constants
import com.romanik.bigdigitalstest.core.fragment.BaseFragment
import com.romanik.bigdigitalstest.databinding.PhotoLinksFragmentBinding
import com.romanik.bigdigitalstest.domain.model.Photo
import com.romanik.bigdigitalstest.ui.list_links.photos_adapter.PhotosAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoLinksFragment : BaseFragment(), PhotosAdapter.OnClickPhotoListener {

    companion object {
        fun newInstance() = PhotoLinksFragment()
    }

    override val viewModel: PhotoLinksViewModel by viewModel()
    override val layout: Int = R.layout.photo_links_fragment
    private lateinit var photoLinksFragmentBinding: PhotoLinksFragmentBinding
    private val photosAdapter by lazy { PhotosAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoLinksFragmentBinding = dataBinding as PhotoLinksFragmentBinding
        with(photoLinksFragmentBinding) {
            this.viewModel = viewModel
            lifecycleOwner = this@PhotoLinksFragment
            rvPhotos.apply {
                val linearLayoutManager = LinearLayoutManager(requireContext())
                val divider = DividerItemDecoration(requireContext(), linearLayoutManager.orientation)
                layoutManager = linearLayoutManager
                adapter = photosAdapter
                addItemDecoration(divider)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            photos.observe(this@PhotoLinksFragment, Observer {
                if (it.isEmpty()) {
                    photoLinksFragmentBinding.rvPhotos.visibility = View.GONE
                    photoLinksFragmentBinding.tvEmptyPhotos.visibility = View.VISIBLE
                } else {
                    photosAdapter.setData(it)
                    photoLinksFragmentBinding.rvPhotos.visibility = View.VISIBLE
                    photoLinksFragmentBinding.tvEmptyPhotos.visibility = View.GONE
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_photos_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.itemSort -> {
                clickSort()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clickSort() {
        viewModel.photos.removeObservers(this)
        viewModel.getSortedPhotosByDateDesc().observe(this, Observer { photosAdapter.setData(it) })
    }

    override fun onClick(photo: Photo) {
        openSecondApp(photo)
    }

    private fun openSecondApp(photo: Photo) {
        val intentSecondApp = requireContext().packageManager.getLaunchIntentForPackage(Constants.SECOND_APP_PACKAGE)
        if (intentSecondApp != null) {
            intentSecondApp.apply {
                putExtra(Constants.PHOTO_ID, photo.id)
                putExtra(Constants.LINK, photo.link)
                putExtra(Constants.STATUS, photo.status.value)
                putExtra(Constants.DATE, photo.date.time)
                putExtra(Constants.WHERE_FROM, Constants.LIST_OF_LINKS_SCREEN)
            }
            startActivity(intentSecondApp)
        } else {
            val throwable = Throwable("Required application not installed")
            viewModel.errorHandler(throwable)
        }

    }

}
