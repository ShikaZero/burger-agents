package model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import bdd.AppDatabase
import com.App

class BurgerViewModel : ViewModel() {

    val eventViewModel: LiveData<MutableList<Burger>> = App.db.BurgerListDao().getAllBurgers()

}