package com.example.developerstools.activity.notificationview

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.developerstools.MainActivity

class AlarmNotification:BroadcastReceiver(){

    companion object{
        const val NOTIFICATION_ID =1
    }

    override fun onReceive(context: Context, p1: Intent?) {
       createSimpleNotification(context)
    }


    private fun createSimpleNotification(context: Context){

        //This block configuration open application onclick notification
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK    //CONFIGURATION FLAG INTENT
        }


        //val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0; //This check only for sdk < 24
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        ////////

        val notification = NotificationCompat.Builder(context, NotificationActivity.MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.btn_dialog)
            .setContentTitle("TITLE")
            .setContentText("Example text xD")
            .setStyle(
                NotificationCompat.BigTextStyle().bigText("Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\n" +
                    "molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\n" +
                    "numquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\n" +
                    "optio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\n" +
                    "obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam"))

            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID,notification)
    }
}