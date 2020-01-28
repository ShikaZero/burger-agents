package model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.App

class BurgerViewModel : ViewModel() {
    val eventViewModel: LiveData<MutableList<Burger>> = App.db.BurgerListDao().getAllBurgers()
}