package ipca.grupo2.backend

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.*
import com.google.android.gms.location.*
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.tasks.await


class Location {
    private val PERMISSIONID = 2
    private lateinit var context: Context
    private lateinit var activity: Activity
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    constructor(context: Context, activity: Activity) {
        this.context = context
        this.activity = activity
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    }

    //this function will return a boolean
    //true: if we have permission
    //false if not
    fun CheckPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    fun RequestPermission() {
        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSIONID
        )
    }

    fun isLocationEnabled(): Boolean {
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation() : Location? {
        var location: Location? = null
        if(CheckPermission()){
            if(isLocationEnabled()){
                return try {
                    fusedLocationProviderClient.lastLocation.addOnCompleteListener {task->
                        location = task.result
                        if(location == null)
                            NewLocationData()
                    }.await()
                } catch (e: java.lang.Exception){
                    location
                }
            }else{
                Toast.makeText(context,"Device Location Off", Toast.LENGTH_SHORT).show()
            }
        }else{
            RequestPermission()
        }
        return location
    }

    suspend fun getAltitude() : Double{
        var loc = getLastLocation() ?: return 0.0
        return loc.altitude
    }

    suspend fun getLatitude() : Double{
        var loc = getLastLocation() ?: return 0.0
        return loc.latitude
    }

    suspend fun getLongitude() : Double{
        var loc = getLastLocation() ?: return 0.0
        return loc.longitude
    }

    @SuppressLint("MissingPermission")
    fun NewLocationData(){
        var locationRequest =  LocationRequest()
        locationRequest.priority = android.location.LocationRequest.QUALITY_BALANCED_POWER_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()
        )
    }


    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
            Toast.makeText(context, "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(lastLocation.latitude,lastLocation.longitude),
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCityName(lat: Double,long: Double):String{
        var geoCoder = Geocoder(context, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat, long,3)

        return Adress.get(0).locality
    }

    private fun getCountryName(lat: Double, long: Double): String{
        var geoCoder = Geocoder(context, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat, long,3)

        return Adress.get(0).countryName
    }
}