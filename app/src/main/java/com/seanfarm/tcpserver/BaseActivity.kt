package com.seanfarm.tcpserver

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

open class BaseActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    val neededPermissions = arrayOf(
        Manifest.permission.INTERNET
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreated")
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE)
        setTitle(TAG)
        if (needRequestPermissions())
            requestNeededPermissions()
        else {
            initAll()
        }
    }

    private val REQUEST_PERMISSION_CODE: Int = 101

    private fun requestNeededPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermissions(neededPermissions, REQUEST_PERMISSION_CODE)
    }

    private fun needRequestPermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            for (permission in neededPermissions) {

                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return true
                }
            }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (needRequestPermissions()) {
                Toast.makeText(this, R.string.permission_denied_close_app, Toast.LENGTH_SHORT).show()
                finish()
            }

        } else {
            initAll()
        }
    }

    private fun initAll() {
        loadPreferences()
        initUI()
    }

    open fun initUI() {

        setContentView(R.layout.activity_dummy)
    }

    protected lateinit var pref: SharedPreferences

    open fun loadPreferences() {
        pref = PreferenceManager.getDefaultSharedPreferences(this)

    }

    open fun savePreferences() {
        pref = PreferenceManager.getDefaultSharedPreferences(this)
    }

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }


}