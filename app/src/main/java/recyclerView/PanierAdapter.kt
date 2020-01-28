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

class PanierAdapter(private val burgers: MutableList<Burger>): RecyclerView.Adapter<PanierAdapter.ViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int { return burgers.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val decimalFormatD = DecimalFormat("0.00")
        val burger = burgers[position]
        with(holder){
            titleMenu.text = burger.title
            priceMenu.text = decimalFormatD.format(burger.price/100)+" €"
            Picasso.get().load(burger.thumbnail).into(holder.photoMenuBasket)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleMenu = itemView.findViewById<TextView>(R.id.titleMenuBasket)
        val priceMenu = itemView.findViewById<TextView>(R.id.priceBasketMenu)
        val photoMenuBasket = itemView.findViewById<ImageView>(R.id.photoMenuBasket)
    }

}




