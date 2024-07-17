package com.projectbasic.alarmmanager

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.projectbasic.alarmmanager.Notification.CHANNEL
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class AlarmReciever : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        Toast.makeText(context, "Recieved", Toast.LENGTH_LONG).show()
        val message = intent?.getStringExtra("Message")
        GlobalScope.launch {
            if (context != null) {
                perfomApiCall(context)
            }
        }
    }

    private suspend fun perfomApiCall(context: Context) {
        val response = QuoteApi.retrofitInstance.fetchQuote()
        if (response.isSuccessful) {
            showNotificationViaManager(context, response.body()?.quote.toString())
        }
    }

    fun showNotificationViaManager(
        context: Context,
        quote: String,
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification =
            NotificationCompat
                .Builder(context, CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(quote)
                .setContentTitle("Quote")
                .build()
        notificationManager.notify(Random.nextInt(), notification)
    }
}
