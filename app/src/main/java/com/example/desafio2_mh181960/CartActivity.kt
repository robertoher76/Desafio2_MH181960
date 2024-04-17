package com.example.desafio2_mh181960


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2_mh181960.adapters.CartAdapter
import com.example.desafio2_mh181960.adapters.MedicineAdapter
import com.example.desafio2_mh181960.models.Medicine
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CartActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        database = Firebase.database.reference
        var medicines = ArrayList<Medicine>()


        database.child("Carrito").get().addOnSuccessListener {
            Log.i("Carrito",it.value.toString())
            it.child("pedido").children.forEach{
                var id = it.key.toString()
                var nombre = it.child("nombre").value.toString()
                var precio = it.child("precio").value.toString().toDouble()
                var medicina = Medicine(id,nombre,precio)
                medicines.add(medicina)
            }
            val recyclerview = findViewById<RecyclerView>(R.id.RecyclerViewMedicinesShopping)
            recyclerview.layoutManager = LinearLayoutManager(this)


            val adapter = CartAdapter(medicines)
            recyclerview.adapter = adapter
        }.addOnFailureListener {
            Log.e("Error obteniendo carrito de pedido", "Error getting data", it)
        }
    }
    fun onClickRegresar(v: View?) {
        val regresar = Intent(this, MainActivity::class.java)
        startActivity(regresar)
    }


    fun onClickConfirmarPedido(v: View?) {
        database.child("Carrito").child("pedido").get().addOnSuccessListener {
            var key = database.child("Historial").push().key.toString()
            var pedido = ArrayList<Medicine>()
            it.children.forEach{
                var id = it.key.toString()
                var nombre = it.child("nombre").value.toString()
                var precio = it.child("precio").value.toString().toDouble()
                var medicina = Medicine(id,nombre,precio)
                pedido.add(medicina)
            }

            database.child("Historial").child(key).setValue(pedido)
            database.child("Carrito").child("pedido").removeValue()
            Toast.makeText(this, "Pedido confirmado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Log.e("firebase", "Error generando historial de pedidos", it)
        }
    }
}
