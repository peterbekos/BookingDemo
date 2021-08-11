package dev.nda.bookingdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.nda.bookingdemo.R
import dev.nda.bookingdemo.model.RoomModel

class RoomAdapter(val rooms: List<RoomModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.room_image)
        val name = itemView.findViewById<TextView>(R.id.room_name_text)
        val availability = itemView.findViewById<TextView>(R.id.spots_available_text)

    }
}