package salgadomontoya.anasofia.myreduce

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_home_blog.*
import kotlinx.android.synthetic.main.celda_imagen.view.*
import kotlinx.android.synthetic.main.cell_pictures.*
import kotlinx.android.synthetic.main.cell_pictures.view.*

class HomeBlog : AppCompatActivity() {


    var entradas = ArrayList<EntradaBlog>()
    var adaptador:HomeEntradasAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_blog)

        val buttonRegresar : ImageButton =findViewById(R.id.retroceder) as ImageButton
        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent(this, PaginaPrincipal::class.java)
            startActivity(intent)
        }

        cargarEntradas()
        adaptador = HomeEntradasAdapter(this, entradas)
        list_entradas.adapter = adaptador
    }
    fun cargarEntradas(){
        entradas.add(EntradaBlog( R.drawable.articulo_5_5, true, true, "Urbanización extrema","Lucia Mendez",R.drawable.perfil2,"Todo hoy en día esta rodeado de smog y de humos nocivos"))
        entradas.add(EntradaBlog( R.drawable.articulo_6_6, true, true, "Cielo en tierra","Mario Costa",R.drawable.perfil3,"Limpiemos nuestros hogares, ya que ese es el primer paso"))
        entradas.add(EntradaBlog( R.drawable.articulo_7_7, true, true, "Luces","Maria Peña",R.drawable.perfil4,"No desperdiciemos la electricidad"))
    }

    class HomeEntradasAdapter: BaseAdapter {
        var context: Context? =null
        var entradas = ArrayList<EntradaBlog>()

        constructor(context: Context, entradas: ArrayList<EntradaBlog>){
            this.context = context
            this.entradas = entradas
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var entrada = entradas[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.cell_pictures, null)
            Log.d("objeto", entrada.toString())
            vista.pictures_set2.setImageResource(entrada.imagen)
            vista.button_estrella.setImageResource(R.drawable.estrella_)
            vista.button_corazon.setImageResource(R.drawable.corazon_)

            //   vista.lista_contenido.setImageResourse(entrada.imagen)
            //  vista.button_corazon.setImageResource(R.drawab
            //  le)
            vista.pictures_set2.setOnClickListener{
                var intento = Intent(context, Detalle_articulo::class.java)
                intento.putExtra("imagenArticulo", entrada.imagen)
                intento.putExtra("titulo",entrada.titulo)
                intento.putExtra("autor",entrada.autor)
                intento.putExtra("imagen",entrada.imagenDelAutor)
                intento.putExtra("contenido",entrada.contenido)
                context!!.startActivity(intento)
            }
            return vista
        }

        override fun getItem(position: Int): Any {
            return 1
        }

        override fun getItemId(position: Int): Long {
            return 1
        }

        override fun getCount(): Int {
            return entradas.size
        }
    }



}
