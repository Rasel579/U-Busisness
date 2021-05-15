package app.u_business.presentation.ui.gallery

import app.u_business.R
import app.u_business.databinding.FrGalleryBinding
import app.u_business.presentation.ui.base.BaseFragment

class GalleryFragment(override val layoutId: Int = R.layout.fr_gallery) :
    BaseFragment<FrGalleryBinding>() {

    private val picturesAdapter: PicturesAdapter by lazy { PicturesAdapter() }

    override fun initViews() {
        initRv()
        mockAdapter()
    }
    private fun initRv() {
        binding.galleryPicturesRv.adapter = picturesAdapter
    }

    private fun mockAdapter() {
        picturesAdapter.data = listOf(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        )
    }
}