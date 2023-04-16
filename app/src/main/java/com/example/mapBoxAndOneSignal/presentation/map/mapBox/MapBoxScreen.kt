import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
//import com.mapbox.maps.MapboxMaps
import com.mapbox.maps.loader.MapboxMaps

@Composable
fun MapBoxScreen(
    onMapReady: (MapboxMap) -> Unit
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            MapView(context).apply {
                getMapboxMap().loadStyleUri(Style.LIGHT) {
                    onMapReady(getMapboxMap())
                }
                MapboxMaps.INSTANCE
            }
        },
        update = { mapView ->
            mapView.onStart()
        }
    )
}
