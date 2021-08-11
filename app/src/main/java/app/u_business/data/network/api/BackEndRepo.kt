package app.u_business.data.network.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BackEndRepo {
    val api : ServiceApi by lazy {

        val gson = GsonBuilder()
            .setLenient()
            .create()

       val adapter = Retrofit.Builder()
           .baseUrl(ApiUtils.baseUrl)
           .addConverterFactory(GsonConverterFactory.create(gson))
           .client(ApiUtils.getOkHttpClient())
           .build()
        adapter.create(ServiceApi::class.java)
    }

    val paymentApi : PaymentApi by lazy {

        val gson = GsonBuilder()
            .setLenient()
            .create()

       val adapter = Retrofit.Builder()
           .baseUrl(ApiUtils.paymentBaseUrl)
           .addConverterFactory(GsonConverterFactory.create(gson))
           .client(ApiUtils.getOkHttpClient())
           .build()
        adapter.create(PaymentApi::class.java)
    }
}