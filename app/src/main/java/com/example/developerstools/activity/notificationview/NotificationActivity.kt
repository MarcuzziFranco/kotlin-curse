package com.example.developerstools.activity.notificationview

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.developerstools.MainActivity
import com.example.developerstools.R
import com.example.developerstools.activity.notificationview.AlarmNotification.Companion.NOTIFICATION_ID
import com.example.developerstools.databinding.ActivityNotificationBinding
import java.util.Calendar

class NotificationActivity : AppCompatActivity() {

    companion object{
        const val MY_CHANNEL_ID = "MY_CHANNEL"
    }

    private lateinit var  binding:ActivityNotificationBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicialice channel notification
        createChannel()

        binding.btLaunchNotification.setOnClickListener{
            scheudleNotification()
        }
    }

    private fun scheudleNotification() {
        val intent = Intent(applicationContext,AlarmNotification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager= getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,Calendar.getInstance().timeInMillis + 15000,pendingIntent)
    }


    private fun createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "NAME_CHANEL",
                NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    description="Description view log"
                }

            val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
}