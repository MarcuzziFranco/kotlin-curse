package com.example.developerstools.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ManagerPermission(context: Context) {
    private var _context: Context
    private var _activityContext: Activity
    private var statusPermission: StatusPermission = StatusPermission.REFUSE
    private lateinit  var permission:Permission

    private lateinit var funActionAccepted: () -> Unit
    private lateinit var funActionRefuse: () -> Unit
    private lateinit var funActionRequireManualActivation: () -> Unit

    init {
        this._context = context
        this._activityContext = context as Activity;
    }

    fun checkPermissions(code:Int, namePermission:String){
        permission = Permission(code,namePermission,statusPermission);

        if (ContextCompat.checkSelfPermission(_context, permission.namePermission) != PackageManager.PERMISSION_GRANTED) {
            //Permiso no aceptado por el momento.
            requestCameraPermission()
        } else {
            //Permisos aceptados defenitivamente.
            statusPermission = StatusPermission.ACCEPTED
            funActionAccepted()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(_activityContext, permission.namePermission)) {
            //El usuario ya ah rechazado los permisos.
            statusPermission = StatusPermission.REQUIRE_MANUAL_ACTIVATION
            funActionRequireManualActivation()
        } else {
            //Perdir permisos
            ActivityCompat.requestPermissions(_activityContext, arrayOf(permission.namePermission),permission.code)
        }
    }

    fun checkRequestPermissionsResult(requestCode: Int,grantResults: IntArray) {
        //Chequeamos los codigos de nuestros permisos
        if (requestCode == permission.code) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Aceptados.
                statusPermission = StatusPermission.ACCEPTED;
                funActionAccepted();
            } else {
                //No fueron aceptados.
                statusPermission = StatusPermission.REFUSE;
                funActionRefuse()
            }
        }
    }

    fun defineActions(
        accepted: () -> Unit,
        refused: () -> Unit,
        requireManualActivation: () -> Unit
    ) {
        funActionAccepted = accepted
        funActionRefuse = refused
        funActionRequireManualActivation = requireManualActivation
    }

}