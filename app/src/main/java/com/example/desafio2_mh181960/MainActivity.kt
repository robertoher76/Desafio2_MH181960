package com.example.desafio2_mh181960

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2_mh181960.adapters.MedicineAdapter
import com.example.desafio2_mh181960.models.Medicine;
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference
        var medicines = ArrayList<Medicine>()

        medicines.add(Medicine("example","example xd",3.99))

        database.child("medicamentos").get().addOnSuccessListener {
            it.children.forEach{
                medicines.add(Medicine(it.key,it.child("nombre").value.toString(),it.child("precio").value.toString().toDouble()))
            }
        }

        val recyclerview = findViewById<RecyclerView>(R.id.RecyclerViewMedicines)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = MedicineAdapter(medicines)
        recyclerview.adapter = adapter
    }
}