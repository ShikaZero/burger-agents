package training.archcomps.burger_agents_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import controller.getAllBurgersController
import kotlinx.android.synthetic.main.activity_main.*
import model.Burger
import model.BurgerViewModel
import recyclerView.BurgerListAdapter
import timber.log.Timber
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() , BurgerListAdapter.BurgerAdapterListener{

    private lateinit var viewModel: BurgerViewModel
    private lateinit var burgerListAdapter : BurgerListAdapter
    private var listRefBurger : ArrayList<String> = ArrayList()
    private var totalPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllBurgersController(this)

        viewModel = ViewModelProviders.of(this).get(BurgerViewModel::class.java)
        viewModel.eventViewModel.observe(this, Observer { newBurgers -> fillBurgerList(newBurgers)})

        validPanierButton.setOnClickListener { goBasket() }
    }

    private fun goBasket() {
       val intent = Intent(this, BasketActivity::class.java)
       intent.putStringArrayListExtra("LIST_REF_BURGER", listRefBurger)
       intent.putExtra("TOTAL_PRICE", totalPrice)
       startActivity(intent)
    }

    private fun fillBurgerList(newBurgers: MutableList<Burger>?) {
        burgerListAdapter = BurgerListAdapter(newBurgers!!, this)
        recyclerViewBurger.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BurgerListAdapter(newBurgers, this@MainActivity)
        }
    }

    override fun addBurgerOnMenu(burger: Burger) {
        totalPrice += burger.price / 100
        listRefBurger.add(burger.ref)
        setTotalPrice()
    }

    override fun removeBurgerFromMenu(burger: Burger) {
        totalPrice -= burger.price / 100
        listRefBurger.remove(burger.ref)
        setTotalPrice()
    }

    fun setTotalPrice(){
        val decimalFormatD = DecimalFormat("0.00")
        when{ totalPrice < 0 -> totalPrice = 0.0}
        totalPriceId.text = decimalFormatD.format(totalPrice)+" €"
    }
}
