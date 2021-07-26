package app.u_business.data.repo.auth

import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.utils.SharedPreferencesHelper

class AuthRepositoryImpl(private val prefs: SharedPreferencesHelper): AuthRepository{

    override fun isFirstOpen(): Boolean = prefs.isFirstOpen

    override fun isAuthed(): Boolean = prefs.isAuthed
}