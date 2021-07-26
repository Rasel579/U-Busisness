package app.u_business.domain.repo.auth

interface AuthRepository {
    fun isFirstOpen(): Boolean

    fun isAuthed(): Boolean

}