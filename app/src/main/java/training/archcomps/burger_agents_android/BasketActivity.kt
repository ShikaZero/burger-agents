package training.archcomps.burger_agents_android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import bdd.AppDatabase
import bdd.BurgerListDao
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.activity_main.*
import model.Burger
import recyclerView.BurgerListAdapter
import recyclerView.PanierAdapter
import timber.log.Timber
import java.text.DecimalFormat

class BasketActivity : AppCompatActivity() {

    private lateinit var burgerListDao: BurgerListDao
    private lateinit var panierAdapter: PanierAdapter
    private var listRefBurger : ArrayList<String> = ArrayList()
    private var totalPrice = 0.0
    private var burgersBasketList : MutableList<Burger> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        //TODO : On prépare les données nécéssaire à l'affichage du panier
        burgerListDao = AppDatabase.getDatabase(this).BurgerListDao()
        listRefBurger = intent?.extras!!.getStringArrayList("LIST_REF_BURGER")
        totalPrice = intent?.extras!!.getDouble("TOTAL_PRICE")

        fillRecyclerViewForBasket(this)

        //TODO : Gestion du prix total de la commande
        val decimalFormatD = DecimalFormat("0.00")
        totalPriceBasketId.text = decimalFormatD.format(totalPrice)+" €"

    }

    private fun fillRecyclerViewForBasket(context: Context) {
        burgersBasketList = burgerListDao.getAllBurgersForBasket(listRefBurger)
        Timber.d("BurgersBasketList ==> %s",burgersBasketList)
        panierAdapter = PanierAdapter(burgersBasketList)
        recyclerViewBasket.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PanierAdapter(burgersBasketList)
        }
    }
}
