package salgadomontoya.anasofia.myreduce


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask


class AgregarActividad : AppCompatActivity() {

    lateinit var tituloArticulo: EditText
    lateinit var detalleArticulo: EditText
    lateinit var buttonSave: Button
    lateinit var buttonAgrega: Button
    lateinit var imagen: ImageView
    lateinit var filePath: Uri
    lateinit var firebaseStore: FirebaseStorage
    lateinit var mStorageRef: StorageReference
    private var uploadTask: StorageTask<*>? = null
    lateinit var mDatabaseRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_actividad)
        val buttonAtras : ImageButton=findViewById(R.id.regresar_atras) as ImageButton
        buttonAtras.setOnClickListener {
            var intent: Intent = Intent(this, PaginaPrincipal::class.java)
            startActivity(intent)
        }
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mStorageRef = FirebaseStorage.getInstance().getReference("Images");
        imagen= findViewById(R.id.picture_set3)
        detalleArticulo= findViewById(R.id.detalles_aritulo)
        tituloArticulo= findViewById(R.id.nombre_articulo)
        buttonSave= findViewById(R.id.button_guardar)
        buttonAgrega= findViewById(R.id.button_agrega)
        firebaseStore = FirebaseStorage.getInstance()

        buttonAgrega.setOnClickListener { Filechooser() }

        buttonSave.setOnClickListener {
            uploadFile() }
    }

    private fun getExtension(uri: Uri): String {
        val cr = contentResolver
        val mimeType = MimeTypeMap.getSingleton()
        return mimeType.getExtensionFromMimeType(cr.getType(uri)).toString()
    }


    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    private fun uploadFile() {
        if (filePath != null) {
            val fileReference = mStorageRef.child(
                System.currentTimeMillis()
                    .toString() + "." + getFileExtension(filePath)
            )
            uploadTask = fileReference.putFile(filePath)
                .addOnSuccessListener { taskSnapshot ->

                    Toast.makeText(this, "Upload successful", Toast.LENGTH_LONG)
                        .show()
                    val upload = Agrega(
                        tituloArticulo.text.toString().trim(), detalleArticulo.text.toString().trim(),
                        taskSnapshot.toString()
                    )
                    val uploadId: String = mDatabaseRef.push().key.toString()
                    mDatabaseRef.child(uploadId).setValue(upload)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }


    private fun Filechooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data!= null && data.data!= null) {
            filePath = data.data!!
            imagen.setImageURI(filePath)
        }
    }





    /*private fun guardar() {
        val titulo= tituloArticulo.text.toString().trim()
        val detalle= detalleArticulo.text.toString().trim()


        if(titulo.isEmpty()){
            tituloArticulo.error= "Ingrese un titulo para el articulo"
            return
        }
        if(detalle.isEmpty()){
            detalleArticulo.error= "Ingrese el cuerpo del articulo"
            return
        }
        val ref= FirebaseDatabase.getInstance().getReference("Actividad")
        val actId= ref.push().key
        val actividad = Agrega(actId.toString(),titulo, detalle )
        ref.child(actId.toString()).setValue(actividad).addOnCompleteListener {
            Toast.makeText(applicationContext,"Se agrego la actividad correctamente", Toast.LENGTH_SHORT).show()
        }

    }
*/

}

