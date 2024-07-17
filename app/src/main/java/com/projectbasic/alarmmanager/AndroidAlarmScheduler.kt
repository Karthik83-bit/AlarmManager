package com.projectbasic.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlin.random.Random

class AndroidAlarmScheduler(
    private val context: Context,
) : AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule() {
        Toast.makeText(context, "scheduler", Toast.LENGTH_LONG).show()
        val intent =
            Intent(context, AlarmReciever::class.java).apply {
                putExtra("Message", "karthik")
            }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            60000,
            PendingIntent.getBroadcast(context, Random.nextInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE),
        )
        Toast.makeText(context, "scheduled", Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun cancel() {
        alarmManager.cancelAll()
    }
}
