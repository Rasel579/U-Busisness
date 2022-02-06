package app.u_business.presentation.ui.news

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.R
import app.u_business.data.network.response.news.fetch_news.dateFormat
import app.u_business.data.network.response.news.fetch_news.serverDateFormat
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.utils.showAlertDialog
import kotlinx.coroutines.launch
import retrofit2.Response.error

sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Error(val throwable: Throwable) : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
}

fun <T> BaseFragment<*>.renderData(
    responseState: ResponseState<T>,
    progressId: Int?,
    funSuccess: (ResponseState.Success<T>) -> Unit,
    funError: ((ResponseState.Error) -> Unit)? = null,
    funLoading: (() -> Unit)? = null
) {
    val progress = progressId?.let { requireView().findViewById<ProgressBar>(progressId) }
    when (responseState) {
        is ResponseState.Success -> {
            funSuccess.invoke(responseState)
            progress?.apply { visibility = View.GONE }
        }

        is ResponseState.Loading -> {
            progress?.apply { visibility = View.VISIBLE }
            funLoading?.invoke()
        }

        is ResponseState.Error -> {
            progress?.apply { visibility = View.GONE }
            if (funError != null)
                funError.invoke(responseState)
            else {
                var mes: String? = null
                if (responseState.throwable is retrofit2.HttpException) {
                    when (responseState.throwable.code()) {
                        502 -> mes = resources.getString(R.string.server_unavailable)
                        else -> {
//                            FIXME сделать обработку, когда заработает сервер
//                            val er =
//                                (responseState.throwable).response()
//                                    ?.errorBody()
//                                    ?.string()
//                            if (er != null)
//                                mes = JSONObject(er).getString("message")
//                            else
//                                mes = responseState.throwable.message
                        }
                    }

                } else
                    mes = responseState.throwable.message
                showAlertDialog(resources.getString(R.string.error), mes ?: "")
            }
        }
    }
}

fun <T> BaseViewModel.getData(
    mutableLiveData: MutableLiveData<ResponseState<T>>,
    fun1: suspend () -> T
) {
    viewModelScope.launch {
        try {
            mutableLiveData.value = ResponseState.Loading
            val res = fun1.invoke()
            mutableLiveData.value = ResponseState.Success(res)
        } catch (exception: Exception) {
            mutableLiveData.value = ResponseState.Error(exception)
        }
    }
}

fun parseDate(date: String?) = try {
    dateFormat.format(serverDateFormat.parse(date))
} catch (e: Exception) {
    ""
}