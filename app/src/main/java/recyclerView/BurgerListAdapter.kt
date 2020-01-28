package recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import model.Burger
import training.archcomps.burger_agents_android.R
import java.text.DecimalFormat

class BurgerListAdapter(private val burgers: MutableList<Burger>,
                        private  val listener: BurgerAdapterListener?): RecyclerView.Adapter<BurgerListAdapter.ViewHolder>(), View.OnClickListener {

    interface BurgerAdapterListener {
        fun addBurgerOnMenu(burger: Burger)
        fun removeBurgerFromMenu(burger: Burger)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int { return burgers.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val decimalFormatD = DecimalFormat("0.00")
        val burger = burgers[position]
        with(holder){
            titleMenu.text = burger.title
            descriptionMenu.text = burger.description
            priceMenu.text = decimalFormatD.format(burger.price / 100)+" €"
            Picasso.get().load(burger.thumbnail).into(holder.photoMenu)
            addMenu.tag = burger
            addMenu.setOnClickListener(this@BurgerListAdapter)
            removeMenu.tag = burger
            removeMenu.setOnClickListener(this@BurgerListAdapter)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleMenu = itemView.findViewById<TextView>(R.id.titleMenuBasket)
        val descriptionMenu = itemView.findViewById<TextView>(R.id.descriptionMenuId)
        val priceMenu = itemView.findViewById<TextView>(R.id.priceBasketMenu)
        val photoMenu = itemView.findViewById<ImageView>(R.id.photoMenuBasket)
        val addMenu = itemView.findViewById<TextView>(R.id.addMenu)
        val removeMenu = itemView.findViewById<TextView>(R.id.removeMenu)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addMenu -> listener?.addBurgerOnMenu(v.tag as Burger)
            R.id.removeMenu -> listener?.removeBurgerFromMenu(v.tag as Burger)
        }
    }

}




