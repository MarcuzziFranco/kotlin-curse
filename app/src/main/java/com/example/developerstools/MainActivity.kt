package com.example.developerstools

import android.Manifest
import android.os.Bundle
import android.util.Log
import com.example.developerstools.activity.BaseActivity
import com.example.developerstools.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnPermission.setOnClickListener{

            managerPermission.defineActions(
                ::actionAcceptedPermission,
                ::actionRefusePermission,
                ::actionRequireManualActivation
            )
            managerPermission.checkPermissions(777,Manifest.permission.CAMERA)
        }



    }

    fun actionAcceptedPermission(){
        Log.i("action","actionAcceptedPermission")
    }

    fun actionRefusePermission(){
        Log.i("action","actionRefusePermission")
    }

    fun actionRequireManualActivation(){
        Log.i("action","actionRequireManualActivation")
    }



}