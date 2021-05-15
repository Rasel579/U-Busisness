package app.u_business.presentation.ui.library

import app.u_business.R

enum class Folder(val titleId: Int, val types: String, val bgTintId: Int) {
    DOCUMENTS(R.string.folder_documents, "doc-pdf", R.color.bright_green),
    PHOTOS(R.string.folder_photos, "jpeg-png", R.color.bright_orange),
    VIDEOS(R.string.folder_videos, "mp4", R.color.brighter_blue),
    RECORDINGS(R.string.folder_recordings, "mp3", R.color.bright_violet),
}