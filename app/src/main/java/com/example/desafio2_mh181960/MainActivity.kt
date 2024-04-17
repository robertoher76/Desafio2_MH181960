package com.example.desafio2_mh181960

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2_mh181960.adapters.MedicineAdapter
import com.example.desafio2_mh181960.models.Medicine;
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference
        var medicines = ArrayList<Medicine>()

        database.child("medicamentos").get().addOnSuccessListener {
            it.children.forEach{
                var id = it.key.toString()
                var nombre = it.child("nombre").value.toString()
                var precio = it.child("precio").value.toString().toDouble()
                var medicina = Medicine(id,nombre,precio)
                medicines.add(medicina)
                val recyclerview = findViewById<RecyclerView>(R.id.RecyclerViewMedicines)
                recyclerview.layoutManager = LinearLayoutManager(this)

                val adapter = MedicineAdapter(medicines)
                recyclerview.adapter = adapter
            }
        }.addOnFailureListener {
            Log.e("Error obteniendo medicamentos", "Error getting data", it)
        }
    }
    fun onClickBtnCarrito(v: View?) {
        val carrito = Intent(this, CartActivity::class.java)
        startActivity(carrito)
    }
}