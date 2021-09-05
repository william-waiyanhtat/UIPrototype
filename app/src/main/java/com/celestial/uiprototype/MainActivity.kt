package com.celestial.uiprototype


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


class MainActivity : AppCompatActivity(),OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap

    private lateinit var mclusterManger: ClusterManager<ClusterMarker>

    private lateinit var myClusterManagerRenderer: MyClusterManagerRenderer

    val clusterMakers: ArrayList<ClusterMarker> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

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


        val marker = ClusterMarker(LatLng(16.824702,96.158498),
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