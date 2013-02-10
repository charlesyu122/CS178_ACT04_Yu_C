package com.charlesyu.googlemapsapplication;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	static final LatLng TC = new LatLng(10.35410, 123.91145);
	static final LatLng MAIN = new LatLng(10.30046, 123.88822);
	static final LatLng AYALA = new LatLng(10.3178, 123.9050);
	private GoogleMap map;
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create map
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        final Marker uscTc = map.addMarker(new MarkerOptions().position(TC).title("USC-TC"));
        final Marker uscMain = map.addMarker(new MarkerOptions().position(MAIN).title("USC-MAIN"));
        final Marker ayalaCebu = map.addMarker(new MarkerOptions().position(AYALA).title("AYALA CENTER CEBU"));
        
        // Move the camera instantly to usc-tc with max zoom.
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(TC, map.getMaxZoomLevel()));
        
        // Set Listeners
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			
        	AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        	
			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				alert.setTitle("Want to travel?");
				
				if(marker.equals(uscTc)){
					alert.setMessage("Do you want to go to USC-Main Campus?")
					.setPositiveButton("Sure",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// Animate to usc main
							map.animateCamera(CameraUpdateFactory.newLatLngZoom(MAIN, map.getMaxZoomLevel()));
						}
					  })
					.setNegativeButton("No Thanks",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {}
					});
				} else if(marker.equals(uscMain)){
					alert.setMessage("Do you want to go to Ayala Center Cebu?")
					.setPositiveButton("Sure",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// Animate to usc main
							map.animateCamera(CameraUpdateFactory.newLatLngZoom(AYALA, map.getMaxZoomLevel()));
						}
					  })
					.setNegativeButton("No Thanks",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {}
					});
					
				}else if(marker.equals(ayalaCebu)){
					alert.setMessage("Do you want to go to USC-TC Campus?")
					.setPositiveButton("Sure",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// Animate to usc main
							map.animateCamera(CameraUpdateFactory.newLatLngZoom(TC, map.getMaxZoomLevel()));
						}
					  })
					.setNegativeButton("No Thanks",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {}
					});
				}
				
				AlertDialog alertDialog = alert.create();
				alertDialog.show();
				
				return false;
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
