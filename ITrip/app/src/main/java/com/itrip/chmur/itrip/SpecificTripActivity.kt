package com.itrip.chmur.itrip

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.JsonWriter
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.gms.common.util.JsonUtils
import kotlinx.android.synthetic.main.activity_specific_trip.*
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import org.json.JSONArray






class SpecificTripActivity : AppCompatActivity() {
    private lateinit var menuItems: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_trip)

        val tripName = intent.getStringExtra("itemName")
        val tripTime = intent.getStringExtra("itemAge")

        val callingIntent = intent // Pobierz informację o wycieczce na którą kliknął użytkownik

        supportActionBar?.title = tripName
        supportActionBar?.subtitle = "Podgląd"

        btn_save.setOnClickListener { _ ->
//            var edit_action_btn: Button? = supportActionBar?.customView?.findViewById(edit_action)
//            edit_action_btn?.
//            val item = menu.findItem(R.id.idOfYourMenuItem)
//            item.setVisible(false)
            val url = "http://104.41.220.226:8080/api/trips/2a4f6196-c535-4a72-aab5-3c950d314a28"

            val array = JSONArray()


            val jsonObject = JSONObject()
            try {
                jsonObject.put("tripId", "2a4f6196-c535-4a72-aab5-3c950d314a28")
                jsonObject.put("name", "Krakow")
                jsonObject.put("description", "blah")
                jsonObject.put("start", 1503382260000)
                jsonObject.put("end", 1506060660000)
                jsonObject.put("waypoints", array)
                jsonObject.put("poster", "")
                jsonObject.put("presentation", "")
            } catch (e: JSONException) {
                // handle exception
            }


            val putRequest = object : JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                    Response.Listener { response ->
                        // response
                        Log.d("Response", response.toString())
                    },
                    Response.ErrorListener { error ->
                        // error
                        Log.d("Error.Response", error.toString())
                    }
            ) {

                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    headers["Accept"] = "application/json"
                    params["Content-Type"] = "application/json"
                    params["Authorization"] = "Basic " + Base64.encodeToString("user:1234".toByteArray(), Base64.NO_WRAP)
                    return headers
                }

                override fun getBody(): ByteArray? {
                    try {
                        Log.i("json", jsonObject.toString())
                        return jsonObject.toString().toByteArray(charset("UTF-8"))
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                    }

                    return null
                }
            }

            // Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(putRequest)

            menuItems.findItem(R.id.edit_action).isVisible = true
            Toast.makeText(applicationContext, "Koniec edycji", Toast.LENGTH_SHORT).show()
            supportActionBar?.subtitle = "Podgląd"
            btn_save.isEnabled = true
        }

        btn_move_to_map.setOnClickListener { _ ->
            val intent = Intent(this, ShowTripActivity::class.java)
            intent.putExtra("tripName", tripName)
            intent.putExtra("tripTime", tripTime)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuItems = menu
        val inflater = menuInflater
        inflater.inflate(R.menu.edit_actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.edit_action -> {
            // User chose the "Settings" item, show the app settings UI...
//            item.isEnabled = false
            item.isVisible = false

            btn_save.isEnabled = true

            supportActionBar?.subtitle = "Edycja"
            Toast.makeText(applicationContext, "Edycja", Toast.LENGTH_SHORT).show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
