package com.seanfarm.tcpserver

import kotlinx.android.synthetic.main.activity_server_config.*

class ServerConfigActivity : BaseActivity() {

    var port = 0

    override fun loadPreferences() {
        port = pref.getInt(Common.PORT, Common.DEFAULT_PORT)
    }

    override fun initUI() {
        setContentView(R.layout.activity_server_config)

        etPort.setText(port.toString())

    }
}