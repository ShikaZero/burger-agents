package recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import model.Burger
import training.archcomps.burger_agents_android.R

class BurgerListAdapter(private val burgers: MutableList<Burger>,
                        private  val listener: BurgerAdapterListener?): RecyclerView.Adapter<BurgerListAdapter.ViewHolder>(), View.OnClickListener {

    interface BurgerAdapterListener {
        fun onBurgerSelected(burger : Burger)
        fun addBurgerOnMenu(burger: Burger)
        fun removeBurgerFromMenu(burger: Burger)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int { return burgers.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val burger = burgers[position]
        with(holder){
            titleMenu.text = burger.title
            descriptionMenu.text = burger.description
            priceMenu.text = (burger.price / 100).toString()
            Picasso.get().load(burger.thumbnail).into(holder.photoMenu)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleMenu = itemView.findViewById<TextView>(R.id.titleMenuId)
        val descriptionMenu = itemView.findViewById<TextView>(R.id.descriptionMenuId)
        val priceMenu = itemView.findViewById<TextView>(R.id.priceMenuId)
        val photoMenu = itemView.findViewById<ImageView>(R.id.photoMenuId)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cardViewBurger -> listener?.onBurgerSelected(v.tag as Burger)
            R.id.addMenu -> listener?.addBurgerOnMenu(v.tag as Burger)
            R.id.removeMenu -> listener?.removeBurgerFromMenu(v.tag as Burger)
        }
    }

}




