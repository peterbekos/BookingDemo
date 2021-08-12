package dev.nda.bookingdemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.nda.bookingdemo.R
import dev.nda.bookingdemo.extension.makeGreyScale
import dev.nda.bookingdemo.extension.makeNormalScale
import dev.nda.bookingdemo.model.RoomModel

class RoomAdapter(
    private val rooms: List<RoomModel>,
    private val onRoomSelect: (RoomModel) -> Unit
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = rooms[position]
        val spots = room.spots ?: 0

        holder.name.text = room.name

        with(holder.image) {
            Picasso.get().load(room.thumbnail).into(this)
            if (spots == 0) makeGreyScale() else makeNormalScale()
        }

        with(holder.availability) {
            text = resources.getQuantityString(
                R.plurals.spots_remaining, spots, spots
            )
            if (spots == 0) setTextColor(Color.RED) else setTextColor(Color.BLACK)
        }

        holder.itemView.setOnClickListener { onRoomSelect(room) }
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