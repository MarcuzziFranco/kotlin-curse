package com.example.developerstools.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.developerstools.permission.ManagerPermission

open class BaseActivity : AppCompatActivity() {

    protected lateinit var managerPermission: ManagerPermission
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        managerPermission = ManagerPermission(this)
    }


    protected fun showToast(msn:String){
        Toast.makeText(this,msn, Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        managerPermission.checkRequestPermissionsResult(requestCode,grantResults)
    }
}