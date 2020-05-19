package salgadomontoya.anasofia.myreduce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalle_articulo.*

class Detalle_articulo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_articulo)

        val bundle = intent.extras
        if (bundle != null){
            imagen_articulo.setImageResource(bundle.getInt("imagenArticulo"))
            titulo.setText(bundle.getString("titulo"))
            autor.setText(bundle.getString("autor"))
            perfil.setImageResource(bundle.getInt("imagen"))
            resumen_articulo.setText(bundle.getString("contenido"))
        }

    }
}
