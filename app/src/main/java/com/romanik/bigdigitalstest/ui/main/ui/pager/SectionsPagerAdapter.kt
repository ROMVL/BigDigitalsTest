package com.romanik.bigdigitalstest.ui.main.ui.pager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.romanik.bigdigitalstest.R
import com.romanik.bigdigitalstest.ui.add_link.AddPhotoFragment
import com.romanik.bigdigitalstest.ui.list_links.PhotoLinksFragment

private val TABS = arrayOf(
    AddPhotoFragment.newInstance(),
    PhotoLinksFragment.newInstance()
)

private val TAB_TITLES = arrayOf(
    R.string.add_photo,
    R.string.photos
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return TABS[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TABS.size
    }
}