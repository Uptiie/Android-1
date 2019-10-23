package com.Lambda.rv_camping.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.Lambda.rv_camping.R
import com.Lambda.rv_camping.adapter.PropertiesAdapter
import com.Lambda.rv_camping.model.CampingSpots
import com.Lambda.rv_camping.model.Properties
import com.Lambda.rv_camping.model.Property
import com.Lambda.rv_camping.ui.activities.MainActivity
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_main.view.*

import com.google.android.gms.maps.MapFragment




class MainController : Controller, OnMapReadyCallback {

    var mMap: GoogleMap? = null

    private var item: Properties? = null


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val lat =  0.0
        val lon = item?.properties

    }

    companion object {
        val campingList = mutableListOf(
            CampingSpots("HWY 66",
                "This cool place is on Mile marker nine of HWY 66. Great view and area for kids to play on",
                "1009 wilyamson rd",
                4.99f
                ),
            CampingSpots("UFO Campground",
                "Located at 99 S Ute Dr Nevada Desert, Nevada, 10111. This location is great for campers that like UFOs",

                "11111 sams dr",
                4.99f)

        )


        var propertyListt = mutableListOf<Property>(
            Property(1, "testName", "testDescription", "testAddress", "testCity",
                "testState", "testImage", 1, 1, 1)
        )
    }


    constructor() : super()
    constructor(args: Bundle?) : super(args){
        args?.getSerializable(MainActivity.BUNDLE_KEY)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {

        val view = inflater.inflate(R.layout.activity_main, container, false)


        //getAllProperties()

        view?.myButton?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(AddPlaceController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))
        }
        view.vRecycle.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PropertiesAdapter(LoginController.properties)
        }

        val mapFragment = (R.id.map) as MapFragment
        mapFragment.getMapAsync(this)
/*getCurrentLocation()*/
        return view
    }
    override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)
        view?.findViewById<Button>(R.id.mButtonAddPlace)?.setOnClickListener {
            router.pushController(RouterTransaction.with(AddPlaceController(args))
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler())
            )
        }
    }
    /*
    fun getAllPropertiesNotInUse(){
        val call: Call<List<CampingSpots>> = PlaceApiBuilder.create().getAllProperties()
        call.enqueue(object: Callback<List<CampingSpots>> {
            override fun onFailure(call: Call<List<CampingSpots>>, t: Throwable) {
                Log.i("Networking:", "OnFailure ${t.message}")
            }

            override fun onResponse(call: Call<List<CampingSpots>>, response: Response<List<CampingSpots>>) {
                if(response.isSuccessful){
                    Log.i("Networking", "123 ${response.body()}")

                    }



            }

        })
    }*/


}



