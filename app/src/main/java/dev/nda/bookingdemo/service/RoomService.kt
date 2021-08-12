package dev.nda.bookingdemo.service

import com.google.gson.Gson
import dev.nda.bookingdemo.model.RoomsModel
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await
import java.lang.Exception

class RoomService {

    private val okHttpClient = OkHttpClient()
    private val gson = Gson()

    suspend fun fetchRooms(): RoomsModel? {
        val request = Request.Builder()
        request.url("https://wetransfer.github.io/rooms.json")

        return try {
            val response = okHttpClient.newCall(request.build()).await()
            if (response.isSuccessful) {
                gson.fromJson(response.body?.string() ?: "", RoomsModel::class.java)
            } else {
                // bad response
                null
            }
        } catch (e: Exception) {
            // network or parsing error
            null
        }


    }
}