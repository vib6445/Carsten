package de.thi.uxd.android.carsten;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeliveryActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public MarkerOptions home, delivery;

    public LatLng position = new LatLng(48.081810, 11.512230);
    public LatLng truck = new LatLng(48.099568, 11.529396);


    private static final String TAG = DeliveryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_delivery);

        // Get the SupportMapFragment and register for the callback
        // when the map is ready for use.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        home = new MarkerOptions()
                .position(position)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.homemarker))
                .title("Home");

        delivery = new MarkerOptions()
                .position(truck)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.deliverymarker))
                .title("Delivery");

        Button backButton = findViewById(R.id.btn_back_to_home);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryActivity.this, MainActivity.class);
                intent.putExtra("origin", "ThankYouActivity");
                startActivity(intent);
            }

        });

    }


    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready for use.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Toast.makeText(getApplicationContext(), "Map ready", Toast.LENGTH_SHORT);
        // Customise the styling of the base map using a JSON object defined
        // in a string resource file. First create a MapStyleOptions object
        // from the JSON styles string, then pass this to the setMapStyle
        // method of the GoogleMap object.
        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.style_json)));

        if (!success) {
            Log.e(TAG, "Style parsing failed.");
        }
        // Position the map's camera near Munich, Germany.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.091254, 11.519174), 14.0f));

        googleMap.addMarker(home);
        googleMap.addMarker(delivery);
    }

}
