package com.example.desafio2_mh181960.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2_mh181960.R
import com.example.desafio2_mh181960.models.Medicine
import com.google.firebase.database.FirebaseDatabase


class MedicineAdapter(private val mList: List<Medicine>) : RecyclerView.Adapter<MedicineAdapter.ViewHolder>() {

    public var medicine = new Medicine();

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medicine_adapter, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.nombre

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val btnAddToCart: Button =  itemView.findViewById(R.id.BtnAddToCart)

        init {
            btnAddToCart.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val databaseReference = FirebaseDatabase.getInstance().reference
                    databaseReference.child("Carrito").child("actual").push().setValue(producto)
                }
            })
        }
    }
}