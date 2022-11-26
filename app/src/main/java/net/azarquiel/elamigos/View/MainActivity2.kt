package net.azarquiel.elamigos.View

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import net.azarquiel.elamigos.R
import net.azarquiel.elamigos.ViewModel.AmigosViewModel
import net.azarquiel.elamigos.adapter.amigosadapter
import net.azarquiel.elamigos.databinding.ActivityMain2Binding
import net.azarquiel.elamigos.model.Amigos
import net.azarquiel.elamigos.model.Grupos
import kotlin.random.Random

class MainActivity2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var GrupoAL: ArrayList<Grupos>
    private lateinit var adapter: amigosadapter
    private lateinit var amigosViewModel: AmigosViewModel
    private lateinit var binding: ActivityMain2Binding
    private  lateinit var AmigosAL: ArrayList<Amigos>
    private lateinit var rnd: Random
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        rnd = Random(System.currentTimeMillis())
        initRV()
        amigosViewModel = ViewModelProvider(this).get(AmigosViewModel::class.java)
        showAllAmigos()
        showPalabras()


        binding.fab2.setOnClickListener {
            crearcolega()
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val product = AmigosAL[pos]
                Delete(product)

            }
        }).attachToRecyclerView( binding.cm.rvamigo)

    }

    private fun Delete(amigos: Amigos) {
        amigosViewModel.delete(amigos.id)
        amigosViewModel.update(amigos)

    }


    private fun crearcolega() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialogonuevoamigo, null)
        val spinner = dialogLayout.findViewById<Spinner>(R.id.spinner)
        initspinner(spinner)
        builder.setView(dialogLayout)

        builder.setTitle("Add Colega")
        builder.setPositiveButton("Ok") { dialog, which ->

        }
        builder.setNegativeButton("Cancel") { dialog, which ->

        }
        builder.show()

    }

    private fun initspinner(spinner: Spinner) {
        val propietariostxt = GrupoAL.mapIndexed { index, propietario -> propietario.nombre }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, propietariostxt)

// Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// attaching data adapter to spinner
        spinner.onItemSelectedListener = this
        spinner.adapter = adapter


    }

    private fun Crear(amigos: Amigos) {

        amigosViewModel.insertA(amigos)

    }


    private fun initRV() {
        adapter = amigosadapter(this, R.layout.row_2)
        binding.cm.rvamigo.adapter = adapter
        binding.cm.rvamigo.layoutManager = LinearLayoutManager(this)
    }
    private fun showAllAmigos() {



        amigosViewModel.getAllAmigos().observe(this, Observer { amigo ->
            // Update the cached copy of the products in the adapter.
            amigo?.let { AmigosAL = it as ArrayList<Amigos>
                    showamigos()
            }
        })

    }
    private fun showPalabras() {



        amigosViewModel.getAllGrupos().observe(this, Observer { grupos ->
            // Update the cached copy of the products in the adapter.
            grupos?.let {  GrupoAL = it as ArrayList<Grupos>
                }
        })

    }

    private fun showamigos() {
        adapter.setAmigos(AmigosAL)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val propietario = GrupoAL[position]

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}
