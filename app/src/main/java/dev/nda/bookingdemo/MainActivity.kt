package dev.nda.bookingdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dev.nda.bookingdemo.adapter.RoomAdapter
import dev.nda.bookingdemo.extension.makeGreyScale
import dev.nda.bookingdemo.extension.makeNormalScale
import dev.nda.bookingdemo.model.RoomModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        room_recycler.layoutManager = LinearLayoutManager(this)
        room_recycler.adapter = RoomAdapter(
            listOf(
                RoomModel(
                    "Room A",
                    10,
                    "https://images.unsplash.com/photo-1571624436279-b272aff752b5?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1504&q=80"
                ),
                RoomModel(
                    "Room B",
                    1,
                    "https://images.unsplash.com/photo-1571624436279-b272aff752b5?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1504&q=80"
                ),
                RoomModel(
                    "Room C",
                    5,
                    "https://images.unsplash.com/photo-1571624436279-b272aff752b5?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1504&q=80"
                ),
                RoomModel(
                    "Room D",
                    0,
                    "https://images.unsplash.com/photo-1571624436279-b272aff752b5?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1504&q=80"
                ),
                RoomModel(
                    "Room E",
                    10,
                    "https://images.unsplash.com/photo-1571624436279-b272aff752b5?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1504&q=80"
                ),
            ),
            resources
        ) {
            val spots = it.spots ?: 0

            with(selected_room_bg_image) {
                Picasso.get().load(it.thumbnail).into(this)
                if (spots == 0) {
                    makeGreyScale()
                } else {
                    makeNormalScale()
                }
            }

            with(selected_room_fg_image) {
                Picasso.get().load(it.thumbnail).into(this)
                if (spots == 0) {
                    makeGreyScale()
                } else {
                    makeNormalScale()
                }
            }
            selected_room_name.text = it.name

            with(selected_room_status) {
                text = resources.getQuantityString(R.plurals.spots_remaining, spots, spots)
                if (spots == 0) {
                    setTextColor(Color.RED)
                } else {
                    setTextColor(Color.BLACK)
                }
            }

            book_button.isEnabled = spots > 0

        }
    }
}