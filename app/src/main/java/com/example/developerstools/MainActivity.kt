package com.example.developerstools

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.developerstools.activity.BaseActivity
import com.example.developerstools.activity.apiview.ApiActivity
import com.example.developerstools.activity.recyclerview.RecyclerViewActivity
import com.example.developerstools.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread.sleep(1000)
        screenSplash.setKeepOnScreenCondition{false}


        binding.btPermission.setOnClickListener{

            managerPermission.defineActions(
                ::actionAcceptedPermission,
                ::actionRefusePermission,
                ::actionRequireManualActivation
            )
            managerPermission.checkPermissions(777,Manifest.permission.CAMERA)
        }

        binding.btLoadList.setOnClickListener{
            val intent = Intent(this,RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        binding.btGoApi.setOnClickListener {
            val intent = Intent(this,ApiActivity::class.java)
            startActivity(intent)
        }


    }

    fun actionAcceptedPermission(){
        Log.i("action","actionAcceptedPermission")
        showToast("actionAcceptedPermission");
    }

    fun actionRefusePermission(){
        Log.i("action","actionRefusePermission")
        showToast("actionRefusePermission")
    }

    fun actionRequireManualActivation(){
        Log.i("action","actionRequireManualActivation")
        showToast("actionRequireManualActivation")
    }



}