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
import net.azarquiel.elamigos.model.Grupos


class CustomAdapter(val context: Context,
                    val layout: Int
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Grupos> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setGrupos(grupos: List<Grupos>) {
        this.dataList = grupos
        notifyDataSetChanged()
    }





    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Grupos){

            val tvnombrerow = itemView.findViewById(R.id.tvnombreamigo) as TextView
            val tvemailrow = itemView.findViewById(R.id.tvemail) as TextView
            tvnombrerow.setTextColor(dataItem.color)
            tvemailrow.setTextColor(dataItem.color)
            tvnombrerow.text = dataItem.nombre
            tvemailrow.text = dataItem.email

            (itemView as CardView).setCardBackgroundColor(Util.getTransparentColor(dataItem.color))


            itemView.tag = dataItem

        }

    }
}
