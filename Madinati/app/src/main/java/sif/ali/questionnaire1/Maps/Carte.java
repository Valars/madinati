package sif.ali.questionnaire1.Maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.util.HashMap;
import java.util.Map;

import sif.ali.questionnaire1.AppProperties;
import sif.ali.questionnaire1.R;

public class Carte extends FragmentActivity implements GeoQueryEventListener, GoogleMap.OnCameraChangeListener{


    private static final GeoLocation INITIAL_CENTER = new GeoLocation(47.27660093, -1.50335127);
    private static final int INITIAL_ZOOM_LEVEL = 14;
    private static final String GEO_FIRE_REF = "https://sizzling-heat-8546.firebaseio.com/locations/problems";

    private GoogleMap map;
    private Circle searchCircle;
    private GeoFire geoFire;
    private GeoQuery geoQuery;
    private StringBuilder myKkey;
    private StringBuilder titre;

    private Map<String,Marker> markers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);



        myKkey=new StringBuilder("");
        titre=new StringBuilder("");


        // setup map and camera position
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        this.map = mapFragment.getMap();
        LatLng latLngCenter = new LatLng(INITIAL_CENTER.latitude, INITIAL_CENTER.longitude);
        this.searchCircle = this.map.addCircle(new CircleOptions().center(latLngCenter).radius(1000));
        this.searchCircle.setFillColor(Color.argb(66, 255, 0, 255));
        this.searchCircle.setStrokeColor(Color.argb(66, 0, 0, 0));
        // GeoQuery updateCriteria({radius:circle.getRaduid()/1000})
        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCenter, INITIAL_ZOOM_LEVEL));
        this.map.setOnCameraChangeListener(this);

        Firebase.setAndroidContext(this);

        // setup GeoFire
        this.geoFire = new GeoFire(new Firebase(GEO_FIRE_REF));
        // radius in km
        this.geoQuery = this.geoFire.queryAtLocation(INITIAL_CENTER, 1);

        // setup markers
        this.markers = new HashMap<String, Marker>();


    }

    @Override
    protected void onStop() {
        super.onStop();
        // remove all event listeners to stop updating in the background
        this.geoQuery.removeAllListeners();
        for (Marker marker: this.markers.values()) {
            marker.remove();
        }
        this.markers.clear();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // add an event listener to start updating locations again
        this.geoQuery.addGeoQueryEventListener(this);
    }





    @Override
    public void onKeyEntered(final String key, final GeoLocation location) {
        // Add a new marker to the map
        myKkey.append(key);
        //Log.d("data", trouver_informations(key).toString());

        final StringBuilder desc=new StringBuilder("d");
        new Thread(new Runnable() {
            @Override
            public void run() {












                final Firebase ref = new Firebase(AppProperties.ADRESS);
                final Query queryRef = ref.child("problems").orderByKey().equalTo(key);
                //final Query queryRef=allProblems;
                //Log.d("hana l9it", key);
                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //Log.d("data", dataSnapshot.child("/description").getValue().toString());
                        desc.append(dataSnapshot.child("/description").getValue().toString());



                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });











            }
        });

        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude)).title(desc.toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.a100)));
        markers.put(key, marker);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {



            }
        });



            }

            private StringBuilder trouver_informations(String key) {

                final StringBuilder desc = new StringBuilder("d");
                final Firebase ref = new Firebase(AppProperties.ADRESS);
                final Query queryRef = ref.child("problems").orderByKey().equalTo(key);
                //final Query queryRef=allProblems;
                //Log.d("hana l9it", key);
                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //Log.d("data", dataSnapshot.child("/description").getValue().toString());
                        desc.append(dataSnapshot.child("/description").getValue().toString());
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                return desc;

            }


            @Override
            public void onKeyExited(String key) {
                // Remove any old marker
                Marker marker = this.markers.get(key);
                if (marker != null) {
                    marker.remove();
                    this.markers.remove(key);
                }
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                // Move the marker
                Marker marker = this.markers.get(key);
                if (marker != null) {
                    this.animateMarkerTo(marker, location.latitude, location.longitude);
                }
            }

            @Override
            public void onGeoQueryReady() {
            }

            @Override
            public void onGeoQueryError(FirebaseError error) {
                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("There was an unexpected error querying GeoFire: " + error.getMessage())
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            // Animation handler for old APIs without animation support
            private void animateMarkerTo(final Marker marker, final double lat, final double lng) {
                final Handler handler = new Handler();
                final long start = SystemClock.uptimeMillis();
                final long DURATION_MS = 3000;
                final Interpolator interpolator = new AccelerateDecelerateInterpolator();
                final LatLng startPosition = marker.getPosition();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        float elapsed = SystemClock.uptimeMillis() - start;
                        float t = elapsed / DURATION_MS;
                        float v = interpolator.getInterpolation(t);

                        double currentLat = (lat - startPosition.latitude) * v + startPosition.latitude;
                        double currentLng = (lng - startPosition.longitude) * v + startPosition.longitude;
                        marker.setPosition(new LatLng(currentLat, currentLng));

                        // if animation is not finished yet, repeat
                        if (t < 1) {
                            handler.postDelayed(this, 16);
                        }
                    }
                });
            }

            private double zoomLevelToRadius(double zoomLevel) {
                // Approximation to fit circle into view
                return 16384000 / Math.pow(2, zoomLevel);
            }

            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                // Update the search criteria for this geoQuery and the circle on the map
                LatLng center = cameraPosition.target;
                double radius = zoomLevelToRadius(cameraPosition.zoom);
                this.searchCircle.setCenter(center);
                this.searchCircle.setRadius(radius);
                this.geoQuery.setCenter(new GeoLocation(center.latitude, center.longitude));
                // radius in km
                this.geoQuery.setRadius(radius / 1000);
            }
        }
