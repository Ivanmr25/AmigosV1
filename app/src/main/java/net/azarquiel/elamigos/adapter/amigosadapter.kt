package net.azarquiel.elamigos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.elamigos.R
import net.azarquiel.elamigos.Util.Util
import net.azarquiel.elamigos.model.Amigos

class amigosadapter(val context: Context,
                    val layout: Int
) : RecyclerView.Adapter<amigosadapter.ViewHolder>() {

    private var dataList2: List<Amigos> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder2: ViewHolder, position: Int) {
        val item2 = dataList2[position]
        holder2.bind(item2)
    }

    override fun getItemCount(): Int {
        return dataList2.size
    }

    internal fun setAmigos(amigo: List<Amigos>) {
        this.dataList2 = amigo
        notifyDataSetChanged()
    }





    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Amigos){

            val tvnombrerow = itemView.findViewById(R.id.tvnombre) as TextView
            tvnombrerow.setTextColor(dataItem.colorfriend)
            tvnombrerow.text = dataItem.nombre
            (itemView as CardView).setCardBackgroundColor(Util.getTransparentColor(dataItem.colorfriend))
            itemView.tag = dataItem

        }

    }




}