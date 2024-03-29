package com.example.ibeer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.ibeer.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding
    private lateinit var model : DetailsActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("id", 0)
        model = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)
        detallesCerveza(id)
        binding.bBuscar.setOnClickListener {
            detallesCerveza(binding.etDetails.text.toString().toInt())
        }
    }

    fun detallesCerveza(id: Int){
        id?.let {
            if (it in 1..25) {
                binding.progressBar.visibility = View.VISIBLE
                GlobalScope.launch(Dispatchers.IO) {
                    val resultado = model.getSingleItem((it).toString())
                    withContext(Dispatchers.Main) {
                        binding.tvResultados.text = resultado?.get(0)?.name
                        if (resultado?.get(0)?.image_url != "https://images.punkapi.com/v2/keg.png") {
                            Picasso.get().load(resultado?.get(0)?.image_url).into(binding.ivDetalle)
                        } else {
                            Picasso.get()
                                .load("https://i1.wp.com/cervecing.es/wp-content/uploads/404-error-beer-not-found.png?fit=178%2C178&ssl=1).into(holder.imagen")
                                .into(binding.ivDetalle)
                        }
                        var ingredientes = ""
                        for (i in resultado?.get(0)?.ingredients?.malt!!) {
                            ingredientes = ingredientes + " " + i.name + ","
                        }
                        for (i in resultado[0].ingredients.hops) {
                            ingredientes = ingredientes + " " + i.name + ","
                        }
                        ingredientes = ingredientes + " " + resultado.get(0).ingredients.yeast
                        binding.tvResultados2.text =
                            "DESCRIPCIÓN:\n" + resultado[0].description + "\n\nGRADUACIÓN:\n" + resultado[0].abv + "º" + "\n\nINGREDIENTES:\n" + ingredientes.substring(
                                1
                            )
                        binding.ivDetalle.isVisible = true
                        binding.tvResultados.isVisible = true
                        binding.tvResultados2.isVisible = true
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }else{
                binding.progressBar.visibility = View.VISIBLE
                binding.tvResultados.text = "Error, cerveza no encontrada"
                binding.tvResultados2.isVisible = false
                binding.ivDetalle.isVisible = false
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {

        fun createDetailsActivity(context : Context) {
            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }
        fun createDetailsActivity(context: Context, pos: Int) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", pos+1)
            context.startActivity(intent)
        }

    }
}