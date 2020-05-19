package salgadomontoya.anasofia.myreduce

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_pagina_principal.*
import kotlinx.android.synthetic.main.activity_pagina_principal.nombre_usuario
import kotlinx.android.synthetic.main.activity_pagina_principal.view.*
import kotlinx.android.synthetic.main.celda_imagen.view.*
import kotlinx.android.synthetic.main.cell_pictures.view.*

class PaginaPrincipal : AppCompatActivity() {


    var entradas = ArrayList<EntradaBlog>()
    var adaptador:EntradasAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_principal)

      val bundle= intent.extras
        if(bundle!= null){
            val nombre= bundle.getString("nombreUsuario")
            nombre_usuario.setText(nombre)

        }
        val buttoncasa : ImageButton =findViewById(R.id.blog_principal) as ImageButton
        buttoncasa.setOnClickListener {
            var intent: Intent = Intent(this, HomeBlog::class.java)
            startActivity(intent)
        }

        val buttonmas : ImageButton =findViewById(R.id.mas_blogs) as ImageButton
        buttonmas.setOnClickListener {
            var intent: Intent = Intent(this, AgregarActividad::class.java)
            startActivity(intent)
        }

        cargarEntradas()
        adaptador = EntradasAdapter(this, entradas)
        lista_contenido.adapter = adaptador

    }
    fun cargarEntradas(){
        entradas.add(EntradaBlog( R.drawable.articulo_2_2, false, false, "Mundo Saludable","Lucia Mendez",R.drawable.perfil2,"Cuidemos el planeta para tener un futuro sano y libre de contaminantes"))
        entradas.add(EntradaBlog( R.drawable.articulo_3_3, false, false, "Limpieza Profunda","Mario Costa",R.drawable.perfil3,"Se tiene que tener un anombiente limpio para una vida limpia"))
        entradas.add(EntradaBlog( R.drawable.articulo_4_4, false, false, "Plantas en todas partes","Maria Peña",R.drawable.perfil4,"Cuidemonos el planeta para tener un futuro sano y libre de contaminantes"))
    }


    class EntradasAdapter: BaseAdapter {
        var context: Context? =null
        var entradas = ArrayList<EntradaBlog>()

        constructor(context: Context, entradas: ArrayList<EntradaBlog>){
            this.context = context
            this.entradas = entradas
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var entrada = entradas[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.celda_imagen, null)
            Log.d("objeto", entrada.toString())
            vista.picture_set.setImageResource(entrada.imagen)
            vista.picture_set.layoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)

         //   vista.lista_contenido.setImageResourse(entrada.imagen)
            //  vista.button_corazon.setImageResource(R.drawab
            //  le)
            vista.picture_set.setOnClickListener{
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
