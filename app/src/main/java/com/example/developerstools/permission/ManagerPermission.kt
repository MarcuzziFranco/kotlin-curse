package com.example.developerstools.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.developerstools.permission.Errors.Companion.ERROR_INITIALIZED_FUNCTION_ACTION_ACCEPTED
import com.example.developerstools.permission.Errors.Companion.ERROR_INITIALIZED_FUNCTION_ACTION_MANUAL
import com.example.developerstools.permission.Errors.Companion.ERROR_INITIALIZED_FUNCTION_ACTION_REFUSE


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
            requestPermission()
        } else {
            //Permisos aceptados defenitivamente.
            statusPermission = StatusPermission.ACCEPTED
            runAction(statusPermission)
        }
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(_activityContext, permission.namePermission)) {
            //El usuario ya ah rechazado los permisos.
            statusPermission = StatusPermission.REQUIRE_MANUAL_ACTIVATION
            runAction(statusPermission)
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
                runAction(statusPermission)
            } else {
                //No fueron aceptados.
                statusPermission = StatusPermission.REFUSE;
                runAction(statusPermission)
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

    private fun runAction(statusPermission: StatusPermission){
        when (statusPermission){
            StatusPermission.ACCEPTED -> {
                if (::funActionAccepted.isInitialized) funActionAccepted()
                else throw ExceptionInInitializerError(ERROR_INITIALIZED_FUNCTION_ACTION_ACCEPTED)
            }
            StatusPermission.REFUSE-> {
                if (::funActionRefuse.isInitialized) funActionRefuse()
                else throw ExceptionInInitializerError(ERROR_INITIALIZED_FUNCTION_ACTION_REFUSE)
            }
            StatusPermission.REQUIRE_MANUAL_ACTIVATION ->{
                if (::funActionRequireManualActivation.isInitialized) funActionRequireManualActivation()
                else throw ExceptionInInitializerError(ERROR_INITIALIZED_FUNCTION_ACTION_MANUAL)
            }
        }
    }

}