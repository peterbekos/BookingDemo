package dev.nda.bookingdemo

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dev.nda.bookingdemo.adapter.RoomAdapter
import dev.nda.bookingdemo.extension.makeGreyScale
import dev.nda.bookingdemo.extension.makeNormalScale
import dev.nda.bookingdemo.model.RoomModel
import dev.nda.bookingdemo.service.RoomService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var roomService: RoomService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roomService = RoomService()

        fetchRooms()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_refresh -> {
            fetchRooms()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun populateRooms(rooms: List<RoomModel>) {
        room_recycler.layoutManager = LinearLayoutManager(this)
        room_recycler.adapter = RoomAdapter(
            rooms
        ) { onRoomSelected(it) }
    }

    private fun onRoomSelected(room: RoomModel) {
        room.let {
            val spots = it.spots ?: 0

            with(selected_room_bg_image) {
                Picasso.get().load(it.thumbnail).into(this)
                if (spots == 0) makeGreyScale() else makeNormalScale()
            }

            with(selected_room_fg_image) {
                Picasso.get().load(it.thumbnail).into(this)
                if (spots == 0) makeGreyScale() else makeNormalScale()
            }
            selected_room_name.text = it.name

            with(selected_room_status) {
                text = resources.getQuantityString(R.plurals.spots_remaining, spots, spots)
                if (spots == 0) setTextColor(Color.RED) else setTextColor(Color.BLACK)
            }

            book_button.isEnabled = spots > 0
            book_button.setOnClickListener { bookRoom(room) }
        }
    }

    private fun fetchRooms() {
        recycler_loading_frame.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            val rooms = roomService.fetchRooms()
            runOnUiThread {
                recycler_loading_frame.visibility = View.GONE
                if (rooms?.rooms == null) {
                    showErrorDialog()
                } else {
                    populateRooms(rooms.rooms)
                }
            }
        }
    }

    private fun bookRoom(room: RoomModel) {
        booking_load_frame.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            val response = roomService.bookRoom(room)
            runOnUiThread {
                booking_load_frame.visibility = View.GONE
                if (response != null) {
                    showBookingResultDialog(response.success ?: false)
                } else {
                    showErrorDialog()
                }
            }
        }
    }

    private fun showBookingResultDialog(success: Boolean) {
        AlertDialog.Builder(this)
            .setTitle(if (success) R.string.success_message else R.string.error_message)
            .setNeutralButton(R.string.close) { dialog, _ -> dialog?.cancel() }
            .create().show()
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.error_message)
            .setNeutralButton(R.string.close) { dialog, _ -> dialog?.cancel() }
            .create().show()
    }
}

