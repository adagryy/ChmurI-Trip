package com.itrip.chmur.itrip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.util.Log
import kotlinx.android.synthetic.main.activity_text_view.view.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initializeData()



        val rv: RecyclerView = findViewById(R.id.rv)

        rv.setHasFixedSize(true)

        val llm = LinearLayoutManager(applicationContext)
        rv.layoutManager = llm

        val adapter = RVAdapter(trips, this)
        rv.adapter = adapter

        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }



    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
//            R.id.nav_list -> {
//
//            }
//            R.id.nav_edit -> {
//
//            }
            R.id.nav_maps -> {
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("testKey", "testVak")
                this.startActivity(intent)
            }
            R.id.nav_exit -> {
                this.finish()
                System.exit(0)
            }
            R.id.nav_create -> {
                val intent = Intent(this, CreateNewTripActivity::class.java)
//                intent.putExtra("testKey", "testVak")
                this.startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    internal inner class Trip(var name: String, var age: String, var photoId: Int)

    private var trips: MutableList<Trip> = ArrayList()

    // This method creates an ArrayList that has three Trip objects
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images.
    private fun initializeData() {
        // Tu wg mnie powinien być jakiś mechanizm pobierania wycieczek z serwera
        trips = ArrayList()
        trips.add(Trip("Podróż 1", "2 lata temu", R.drawable.flower))
        trips.add(Trip("Wycieczka 2", "25 lat temu", R.drawable.flower))
        trips.add(Trip("Wyjazd 3", "35 lat temu", R.drawable.flower))
        trips.add(Trip("Podróż 11", "2 lata temu", R.drawable.flower))
        trips.add(Trip("Wycieczka 21", "25 lat temu", R.drawable.flower))
        trips.add(Trip("Wyjazd 31", "35 lat temu", R.drawable.flower))
        trips.add(Trip("Podróż 12", "2 lata temu", R.drawable.flower))
        trips.add(Trip("Wycieczka 22", "25 lat temu", R.drawable.flower))
        trips.add(Trip("Wyjazd 32", "35 lat temu", R.drawable.flower))
        trips.add(Trip("Podróż 13", "2 lata temu", R.drawable.flower))
        trips.add(Trip("Wycieczka 23", "25 lat temu", R.drawable.flower))
        trips.add(Trip("Wyjazd 33", "35 lat temu", R.drawable.flower))
    }

    internal class RVAdapter(private val trips: MutableList<MainActivity.Trip>, private val mainActivity: MainActivity) : RecyclerView.Adapter<RVAdapter.TripViewHolder>() {

        class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            internal var cv: CardView = itemView.findViewById(R.id.cv)
            internal var tripName: TextView = itemView.findViewById(R.id.trip_name)
            internal var tripTime: TextView  = itemView.findViewById(R.id.trip_age)
            internal var tripPhoto: ImageView = itemView.findViewById(R.id.trip_photo)
        }

        override fun getItemCount(): Int {
            return trips.size
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TripViewHolder {
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_text_view, viewGroup, false)
            v.setOnClickListener { item ->
                val index = viewGroup.indexOfChild(item)
                Log.e("Clicked item", index.toString() + ", " + item.trip_age.text + ", " + item.trip_name.text)
                val intent = Intent(mainActivity, SpecificTripActivity::class.java)
                intent.putExtra("itemNumber", index.toString())
                intent.putExtra("itemAge", item.trip_age.text)
                intent.putExtra("itemName", item.trip_name.text)
                mainActivity.startActivity(intent)
            }
            return TripViewHolder(v)
        }

        override fun onBindViewHolder(tripViewHolder: TripViewHolder, i: Int) {
            tripViewHolder.tripName.text = trips[i].name
            tripViewHolder.tripTime.text = trips[i].age
            tripViewHolder.tripPhoto.setImageResource(trips[i].photoId)
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
        }
    }
}

