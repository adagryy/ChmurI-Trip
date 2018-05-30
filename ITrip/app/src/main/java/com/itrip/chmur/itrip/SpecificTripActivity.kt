package com.itrip.chmur.itrip

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_specific_trip.*


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
            menuItems.findItem(R.id.edit_action).isVisible = true
            Toast.makeText(applicationContext, "Koniec edycji", Toast.LENGTH_SHORT).show()
            supportActionBar?.subtitle = "Podgląd"
            btn_save.isEnabled = false
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
