package icoder.itaxi.uz.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import icoder.itaxi.uz.R
import kotlinx.android.synthetic.main.activity_osm_map.*
import kotlinx.android.synthetic.main.app_bar_osm.*
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


class OsmMapActivity : AppCompatActivity() {

    private lateinit var map: MapView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ctx: Context = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        setContentView(R.layout.activity_osm_map)

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        val mapController = map.controller
        mapController.setZoom(16)
        val startPoint = GeoPoint(41.570525, 60.631257)
        mapController.setCenter(startPoint)

        btnMenu.setOnClickListener {
            drawer_layout.openDrawer(Gravity.START, true)
        }
    }


}
