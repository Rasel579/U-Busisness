package app.u_business.presentation.ui.library

import app.u_business.R
import app.u_business.databinding.FrLibraryBinding
import app.u_business.domain.model.File
import app.u_business.presentation.ui.base.BaseFragment

class LibraryFragment(override val layoutId: Int = R.layout.fr_library) :
    BaseFragment<FrLibraryBinding>() {

    private val foldersAdapter: FoldersAdapter by lazy { FoldersAdapter() }
    private val filesAdapter: FilesAdapter by lazy { FilesAdapter() }

    override fun initViews() {
        initRecyclers()
        mockAdapter()
    }

    private fun initRecyclers() {
        with(binding) {
            libraryFoldersRv.adapter = foldersAdapter
            libraryFilesRv.adapter = filesAdapter
        }
    }

    private fun mockAdapter() {
        filesAdapter.data = listOf(
            File(
                "file.pdf",
                1243,
                Folder.DOCUMENTS
            ),
            File(
                "file.pdf",
                1243,
                Folder.VIDEOS
            ),
            File(
                "file.pdf",
                1243,
                Folder.RECORDINGS
            ),
            File(
                "file.pdf",
                1243,
                Folder.PHOTOS
            ),

        )
    }
}