package com.celestial.uiprototype


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.celestial.uiprototype.ui.ClusterMarker
import com.celestial.uiprototype.ui.MyClusterManagerRenderer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hover_layout.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap

    private lateinit var mclusterManger: ClusterManager<ClusterMarker>

    private lateinit var myClusterManagerRenderer: MyClusterManagerRenderer

    val clusterMakers: ArrayList<ClusterMarker> = ArrayList()

    private lateinit var carRecyAdapter: CarRecyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        carRecyAdapter = CarRecyAdapter()

        carRcyView.apply {
            adapter = carRecyAdapter
            layoutManager = LinearLayoutManager(context)

        }

        toggleAnimateView(false)

        hoverView.setOnClickListener{
            toggleAnimateView(toolbar.visibility != View.VISIBLE)

        }

        toolbar.setNavigationOnClickListener {
            toggleAnimateView(false)

        }

    }

    private fun toggleAnimateView(isVisible: Boolean) {
        val viewProp = if (isVisible) View.VISIBLE else View.GONE
        val viewPropBot = if (!isVisible) View.VISIBLE else View.GONE


        toolbar.visibility = viewProp
        carListGroup.visibility = viewProp
        alertBtn.visibility = viewPropBot
        bottomNavigationView.visibility = viewPropBot

        when(isVisible){
           true -> hoverView.setBackgroundColor(Color.WHITE)
            false-> hoverView.setBackgroundColor(Color.TRANSPARENT)

        }

    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(16.823069, 96.161856)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));

        addMapMarker()


    }

    private fun addMapMarker() {
        mclusterManger = ClusterManager<ClusterMarker>(this@MainActivity, mMap)
        myClusterManagerRenderer = MyClusterManagerRenderer(
                this@MainActivity,
                mMap,
                mclusterManger
        )

        mclusterManger.renderer = myClusterManagerRenderer
        mMap.setOnInfoWindowClickListener(this)


        val marker = ClusterMarker(LatLng(16.824702, 96.158498),
                "BarberShop",
                "To Hair Cut",
                R.drawable.ic_car
        )

        val marker1 = ClusterMarker(
                LatLng(16.824959, 96.162360),
                "Massage",
                "To Relax",
                R.drawable.ic_car)

        mclusterManger.addItem(marker)
        mclusterManger.addItem(marker1)
        clusterMakers.add(marker)
        clusterMakers.add(marker1)


    }

    override fun onInfoWindowClick(p0: Marker) {


    }


}