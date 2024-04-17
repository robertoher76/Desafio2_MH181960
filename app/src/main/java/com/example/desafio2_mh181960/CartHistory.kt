package com.example.desafio2_mh181960


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2_mh181960.adapters.CartHistoryAdapter
import com.example.desafio2_mh181960.adapters.MedicineAdapter
import com.example.desafio2_mh181960.models.Medicine
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CartHistory : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_history)


        database = Firebase.database.reference
        var medicines = ArrayList<Medicine>()


        database.child("Historial").get().addOnSuccessListener {
            it.children.forEach {
                it.children.forEach {
                    Log.i("INFO", it.value.toString())
                    var id = it.key.toString()
                    var nombre = it.child("nombre").value.toString()
                    var precio = it.child("precio").value.toString().toDouble()
                    var medicina = Medicine(id, nombre, precio)
                    medicines.add(medicina)
                }
            }
            val recyclerview = findViewById<RecyclerView>(R.id.RecyclerViewHistorial)
            recyclerview.layoutManager = LinearLayoutManager(this)


            val adapter = CartHistoryAdapter(medicines)
            recyclerview.adapter = adapter
        }.addOnFailureListener {
            Log.e("Error obteniendo medicamentos", "Error getting data", it)
        }
    }


    fun onClickRegresar(v: View?) {
        val regresar = Intent(this, MainActivity::class.java)
        startActivity(regresar)
    }
}
