package com.example.ibeer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdapterString : RecyclerView.Adapter<AdapterString.StringViewHolder>(){

    private var cervezas : List<Cerveza>? = null

    class StringViewHolder(root: View, val textView: TextView, val imagen: ImageView) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item, parent, false)

        val textView = view.findViewById<TextView>(R.id.tv_activity_main_item)
        val imagen = view.findViewById<ImageView>(R.id.im_activity_main_item)
        return StringViewHolder(view, textView, imagen)
    }

    override fun onBindViewHolder(holder: AdapterString.StringViewHolder, position: Int) {
        cervezas?.let {
            holder.textView.text = it[position].name
            if(it[position].image_url!="https://images.punkapi.com/v2/keg.png") {
                Picasso.get().load(it[position].image_url).into(holder.imagen)
            }else{
                Picasso.get().load("https://i1.wp.com/cervecing.es/wp-content/uploads/404-error-beer-not-found.png?fit=178%2C178&ssl=1).into(holder.imagen").into(holder.imagen)
            }
        }
    }

    override fun getItemCount(): Int {
        cervezas?.let {
            return it.size
        }
        return 0
    }

    suspend fun setData(string: List<Cerveza>){
        cervezas = string
        withContext(Dispatchers.Main){
            notifyDataSetChanged()
        }
    }
}
