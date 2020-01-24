package training.archcomps.burger_agents_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import controller.getAllBurgersController
import model.Burger
import model.BurgerViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BurgerViewModel
    private lateinit var burgerList: MutableList<Burger>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAllBurgersController(this)

        viewModel = ViewModelProviders

    }
}
