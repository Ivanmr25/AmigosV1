package net.azarquiel.elamigos.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import net.azarquiel.elamigos.R
import net.azarquiel.elamigos.ViewModel.AmigosViewModel
import net.azarquiel.elamigos.adapter.CustomAdapter
import net.azarquiel.elamigos.databinding.ActivityMainBinding
import net.azarquiel.elamigos.model.Grupos
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: CustomAdapter
    private lateinit var amigosViewModel: AmigosViewModel
    private lateinit var binding: ActivityMainBinding
    private  lateinit var GrupoAL: ArrayList<Grupos>
    private lateinit var rnd: Random
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GrupoAL = ArrayList<Grupos>()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rnd = Random(System.currentTimeMillis())
        setSupportActionBar(binding.toolbar)

            initRV()
        amigosViewModel = ViewModelProvider(this).get(AmigosViewModel::class.java)
        showPalabras()


        binding.fab.setOnClickListener {
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
                val product = GrupoAL[pos]

                Delete(product)

            }
        }).attachToRecyclerView( binding.cm.rvcarro)

    }

    private fun Delete(grupos: Grupos) {
        amigosViewModel.deleteg(grupos.idgrupo)
        amigosViewModel.updateg(grupos)

    }


    private fun crearcolega() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Grupo")
        val ll = LinearLayout(this)
        ll.setPadding(30,30,30,30)
        ll.orientation = LinearLayout.VERTICAL

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        lp.setMargins(0,50,0,50)

        val textInputLayoutNombre = TextInputLayout(this)
        textInputLayoutNombre.layoutParams = lp
        val textInputLayoutCantidad = TextInputLayout(this)
        textInputLayoutCantidad.layoutParams = lp
        val textInputLayoutPrecio = TextInputLayout(this)
        textInputLayoutPrecio.layoutParams = lp
        val etnombre = EditText(this)
        etnombre.setPadding(0, 80, 0, 80)
        etnombre.textSize = 20.0F
        etnombre.hint = "ID"
        textInputLayoutNombre.addView(etnombre)

        val etcantidad = EditText(this)
        etcantidad.setPadding(0, 80, 0, 80)
        etcantidad.textSize = 20.0F
        etcantidad.hint = "Nombre"
        textInputLayoutCantidad.addView(etcantidad)

        val etprecio = EditText(this)
        etprecio.setPadding(0, 80, 0, 80)
        etprecio.textSize = 20.0F
        etprecio.hint = "Email"

        textInputLayoutPrecio.addView(etprecio)


        ll.addView(textInputLayoutNombre)
        ll.addView(textInputLayoutCantidad)
        ll.addView(textInputLayoutPrecio)

        builder.setView(ll)

        builder.setPositiveButton("Save") { dialog, which ->
            val color = Color.argb(255, (1..255).random(rnd), (1..255).random(rnd), (1..255).random(rnd))
            Crear(Grupos(etnombre.text.toString().toInt() + 1,etcantidad.text.toString(), etprecio.text.toString() , color))
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
        }

        builder.show()

    }

    private fun Crear(grupos: Grupos) {

        amigosViewModel.insertg(grupos)

    }


    private fun initRV() {
        adapter = CustomAdapter(this, R.layout.row)
        binding.cm.rvcarro.adapter = adapter
        binding.cm.rvcarro.layoutManager = LinearLayoutManager(this)
    }
    private fun showPalabras() {



        amigosViewModel.getAllGrupos().observe(this, Observer { grupos ->
            // Update the cached copy of the products in the adapter.
            grupos?.let {  GrupoAL = it as ArrayList<Grupos>
            showgrupos()}
        })

    }

    private fun showgrupos() {
        adapter.setGrupos(GrupoAL)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_persona ->{
                val intent = Intent(
                    this, MainActivity2::class.java)

                startActivity(intent)
                true
            }









            else -> super.onOptionsItemSelected(item)
        }
    }


}