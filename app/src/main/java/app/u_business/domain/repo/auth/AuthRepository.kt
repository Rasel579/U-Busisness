package app.u_business.domain.repo.auth

import app.u_business.data.network.query.user.RegistrationBody
import retrofit2.Callback

interface AuthRepository {
    fun isFirstOpen(): Boolean
    fun isAuthed(): Boolean
    fun registerNewUser() : Callback<RegistrationBody>
}