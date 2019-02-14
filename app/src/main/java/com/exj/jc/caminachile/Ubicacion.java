package com.exj.jc.caminachile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.exj.jc.caminachile.R.id.btnInicio;

public class Ubicacion extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private Spinner spRegion;
    private Button btnRegion;
    private Button btnRecomendar;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);

        //contenedor mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //selección categoría
        Spinner spCategoria = (Spinner) findViewById(R.id.spRegion);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spRegion, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spCategoria.setAdapter(adapter);

        //botón
        btnRegion = (Button) findViewById(R.id.btnRegion);

        btnRegion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent btnNumero = new Intent(Ubicacion.this, Region.class);
                startActivity(btnNumero);
            }

        });

        btnRecomendar = (Button) findViewById(R.id.btnRecomendar);

        btnRecomendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent btnNumero = new Intent(Ubicacion.this, Recomendar.class);
                startActivity(btnNumero);
            }

        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                                           @Override
                                           public void onMapLongClick(LatLng latLng) {
                                               mMap.addMarker(new MarkerOptions()
                                                       .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcador))
                                                       .anchor(0.0f,1.0f)
                                                       .position(latLng));
                                           }
        });


    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi ubicación")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icono_ubicacion)));
        mMap.animateCamera(miUbicacion);


    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }

    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,0,locListener);
    }


}











