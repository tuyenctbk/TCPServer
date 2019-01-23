package com.seanfarm.tcpserver

class ServerConfigActivity : BaseActivity() {

    var port: String = ""

    override fun loadPreferences() {
        port = pref.
    }
    override fun initUI() {
        setContentView(R.layout.activity_server_config)

    }
}