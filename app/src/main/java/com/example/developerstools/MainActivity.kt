package com.example.developerstools

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.developerstools.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.btnPermission.setOnClickListener{checkPermissions()}
    }

    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //Permiso no aceptado por el momento.
            requestCameraPermission()
        }else {
            //Abrir camara
            openCamera()
        }
    }

    private fun openCamera() {
        showToast("open camera")
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            //El usuario ya ah rechazado los permisos.
            showToast("permission denegate , require activation manual")
        }else{
            //Perdir permisos
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),777)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //Chequeamos los codigos de nuestros permisos
        if(requestCode == 777){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera()
            }else {
                //No fueron aceptado.
                showToast("first permission denegate")
            }
        }
    }

    private fun showToast(msn:String){
        Toast.makeText(this,msn,Toast.LENGTH_LONG).show()
    }
}