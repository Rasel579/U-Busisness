package app.u_business.domain.model

import app.u_business.presentation.ui.library.Folder

data class File(
    val name: String,
    val size: Int,
    val folder: Folder
)
