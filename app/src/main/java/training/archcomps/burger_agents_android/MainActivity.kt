package training.archcomps.burger_agents_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import bdd.AppDatabase
import controller.getAllBurgersController
import kotlinx.android.synthetic.main.activity_main.*
import model.Burger
import model.BurgerViewModel
import recyclerView.BurgerListAdapter
import timber.log.Timber
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() , BurgerListAdapter.BurgerAdapterListener{

    private lateinit var viewModel: BurgerViewModel
    private lateinit var burgerList: MutableList<Burger>
    private lateinit var burgerListAdapter : BurgerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllBurgersController(this)

        viewModel = ViewModelProviders.of(this).get(BurgerViewModel::class.java)
        viewModel.eventViewModel.observe(this, Observer { newBurgers -> fillBurgerList(newBurgers)})

    }

    private fun fillBurgerList(newBurgers: MutableList<Burger>?) {
        burgerListAdapter = BurgerListAdapter(newBurgers!!, this)
        recyclerViewBurger.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BurgerListAdapter(newBurgers, this@MainActivity)
        }
    }

    override fun onBurgerSelected(burger: Burger) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addBurgerOnMenu(burger: Burger) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeBurgerFromMenu(burger: Burger) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
