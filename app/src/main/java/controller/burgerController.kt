package controller

import android.content.Context
import android.util.Log
import android.widget.Toast
import bdd.AppDatabase
import com.App
import model.Burger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.BurgerServices
import timber.log.Timber
import java.util.concurrent.Executors

private var okHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()

private var retrofit = Retrofit.Builder()
    .baseUrl(BurgerServices.ENDPOINT)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private var burgerServices = retrofit.create(BurgerServices::class.java)

fun getAllBurgersController(context: Context){
    burgerServices.getAllBurgers().enqueue(object : Callback<List<Burger>>{
        override fun onResponse(call: Call<List<Burger>>, response: Response <List<Burger>>) {
            val burgerListDao = AppDatabase.getDatabase(context).BurgerListDao()
            Timber.d("Burger ==> %s",response.body().toString())
            Executors.newSingleThreadExecutor().execute {
                burgerListDao.insertEvent(response.body()!!)
            }
        }
        override fun onFailure(call: Call<List<Burger>>, t: Throwable) {
            Toast.makeText(context, "Impossible de récupérer les menus", Toast.LENGTH_SHORT).show()
        }

    })}