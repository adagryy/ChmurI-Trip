package com.itrip.chmur.itrip

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        AppConfigurator.serverDomain = getString(R.string.server_domain)

        val sharedPref = getSharedPreferences(getString(R.string.loginDataKey), Context.MODE_PRIVATE)
        var test = sharedPref.all.get(getString(R.string.loginDataKey))

        AppConfigurator.loginData = sharedPref.all.get(getString(R.string.loginDataKey)).toString()

        testIfUserLoggedIn(AppConfigurator.loginData)
    }

    private fun testIfUserLoggedIn(loginData: String){

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, AppConfigurator.serverDomain + "api/trips", null,
                Response.Listener { response ->
                    this.startActivity(Intent(this, MainActivity::class.java))

                },
                Response.ErrorListener { error ->
                    this.startActivity(Intent(this, LoginActivity::class.java))
                }) { //no semicolon or coma
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/json"
                params["Authorization"] = "Basic " + loginData
                return params
            }
        }

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}
