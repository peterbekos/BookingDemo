package dev.nda.bookingdemo.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.nda.bookingdemo.R
import dev.nda.bookingdemo.model.RoomModel

class RoomAdapter(
    private val rooms: List<RoomModel>,
    private val resources: Resources
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = rooms[position]
        Picasso.get().load(room.thumbnail).into(holder.image)
        holder.name.text = room.name
        holder.availability.text = resources.getQuantityString(
            R.plurals.spots_remaining,
            room.spots ?: 0,
            room.spots ?: 0
        )
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.room_image)
        val name = itemView.findViewById<TextView>(R.id.room_name_text)
        val availability = itemView.findViewById<TextView>(R.id.spots_available_text)

    }
}