package com.example.developerstools.activity.notificationview

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
import com.example.developerstools.databinding.ActivityNotificationBinding

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
            createSimpleNotification()
        }
    }


    fun createSimpleNotification(){

        val intent = Intent(this,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK    //CONFIGURATION FLAG INTENT
        }


        //val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0; //This check only for sdk < 24
        val pendingIntent:PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this,MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.btn_dialog)
            .setContentTitle("TITLE")
            .setContentText("Example text xD")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\n" +
                    "molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\n" +
                    "numquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\n" +
                    "optio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\n" +
                    "obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam"))

            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(1,builder.build())
        }
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