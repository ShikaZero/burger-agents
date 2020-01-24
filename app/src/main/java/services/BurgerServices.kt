package services

import model.Burger
import retrofit2.Call
import retrofit2.http.*

interface BurgerServices {

    //@Headers("Content-Type: application/json")
    @GET("catalog")
    fun getAllBurgers(): Call<List<Burger>>

    companion object {
        val ENDPOINT = "https://bigburger.useradgents.com/"
    }
}