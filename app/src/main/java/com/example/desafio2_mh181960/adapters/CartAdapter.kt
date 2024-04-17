package com.example.desafio2_mh181960.adapters




import android.content.Context
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
class CartAdapter(private val mList: List<Medicine>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    // create new views
    var context: Context? = null
    fun CarritoAdapter(context: Context?) {
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medicine_adapter, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.textView.text = ItemsViewModel.nombre
        holder.medicine = ItemsViewModel
    }
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }


    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val btnAddToCart: Button =  itemView.findViewById(R.id.BtnAddToCart)
        var medicine = Medicine()
        init {
            btnAddToCart.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val databaseReference = FirebaseDatabase.getInstance().reference
                    databaseReference.child("Carrito").child("pedido").child(medicine.id).removeValue()
                }
            })
        }
    }
}