package com.fshangala.lambo1slave

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val sharedPref = getSharedPreferences("MySettings", MODE_PRIVATE)

        val hostIP = findViewById<EditText>(R.id.inputHostIp)
        hostIP.setText(sharedPref.getString("hostIp","192.168.43.145"))

        val hostPort = findViewById<EditText>(R.id.inputHostPort)
        hostPort.setText(sharedPref.getInt("hostPort",8000).toString())

        val hostCode = findViewById<EditText>(R.id.inputCode)
        hostCode.setText(sharedPref.getString("hostCode","sample"))
    }

    override fun onResume() {
        super.onResume()

        val sharedPref = getSharedPreferences("MySettings", MODE_PRIVATE)
        val betSite = BetSite()
        val betSitesAdapter:ArrayAdapter<CharSequence> = ArrayAdapter(this,android.R.layout.simple_spinner_item,betSite.sites)
        betSitesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val hostIP = findViewById<EditText>(R.id.inputHostIp)
        hostIP.post {
            hostIP.setText(sharedPref.getString("hostIp", "192.168.43.145"))
        }

        val hostPort = findViewById<EditText>(R.id.inputHostPort)
        hostPort.post {
            hostPort.setText(sharedPref.getInt("hostPort", 8000).toString())
        }

        val hostCode = findViewById<EditText>(R.id.inputCode)
        hostCode.post {
            hostCode.setText(sharedPref.getString("hostCode", "sample"))
        }

    }

    fun savePreferences(view: View){
        val hostIP = findViewById<EditText>(R.id.inputHostIp)
        val hostPort = findViewById<EditText>(R.id.inputHostPort)
        val hostCode = findViewById<EditText>(R.id.inputCode)

        val sharedPref = getSharedPreferences("MySettings", MODE_PRIVATE)
        val editSharedPref = sharedPref.edit()
        editSharedPref.putString("hostIp",hostIP.text.toString())
        editSharedPref.putInt("hostPort",hostPort.text.toString().toInt())
        editSharedPref.putString("hostCode",hostCode.text.toString())
        editSharedPref.apply()

        finish()
    }

    private fun openMain(){
        val intent = Intent(this,SiteActivity::class.java)
        startActivity(intent)
    }
}