package icoder.itaxi.uz.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnCompleteListener
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateSource
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import icoder.itaxi.uz.R

class MainActivity : AppCompatActivity(), CameraListener {

    private lateinit var mapView: MapView
    private lateinit var layoutBottomBar: FrameLayout
    private lateinit var fadeIn: AlphaAnimation
    private lateinit var fadeOut: AlphaAnimation
    private var onStopCamera = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("8425f5f7-fb30-420f-af54-0fe6676175f5")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        layoutBottomBar = findViewById(R.id.layoutBottomNav)
        fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000
//        fadeIn.startOffset = 1000
        fadeIn.setInterpolator(DecelerateInterpolator())
        fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 1000
//        fadeOut.startOffset = 1000
        fadeOut.setInterpolator(DecelerateInterpolator())


        mapView = findViewById(R.id.mapview)
        mapView.map.move(
            CameraPosition(Point(41.570525, 60.631257), 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1.0f), null
        )

        mapView.map.addCameraListener(this)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onCameraPositionChanged(
        map: Map,
        cameraPosition: CameraPosition,
        cameraUpdateSource: CameraUpdateSource,
        finished: Boolean
    ) {
        if (finished) {
            if (onStopCamera) {
                layoutBottomBar.startAnimation(fadeIn)
                onStopCamera = false
            }
        } else {
            if (!onStopCamera) {
                layoutBottomBar.startAnimation(fadeOut)
                onStopCamera = true
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 51 && resultCode == Activity.RESULT_OK) {
            //getDeviceLOcation()
        }
    }

    private fun configurePermisson() {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET
                    ), 10
                )
            }
            return
        }
    }
}
