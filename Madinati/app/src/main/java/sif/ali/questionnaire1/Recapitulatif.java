package sif.ali.questionnaire1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sif.ali.questionnaire1.Model.Model;
import sif.ali.questionnaire1.Model.Type;

import android.location.Location;

public class Recapitulatif extends AppCompatActivity {

    private TextView typeProb;
    private TextView dateProb;
    private TextView descriptionProb;
    private ImageView imageRecap;
    private String chaine = "";
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button versPrincipale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Firebase ref = new Firebase("https://sizzling-heat-8546.firebaseio.com/problems");

        //GeoFire geoFire = new GeoFire(ref);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recapitulatif);
        Location loc;
        final Firebase ref = new Firebase(AppProperties.ADRESS);

        Intent intent = getIntent();
        final Model model = (Model) intent.getSerializableExtra("Model");
        chaine.concat(model.getDescription().toString());
        //Base64.decode(model.getImage(),Base64.DEFAULT);
        imageRecap = (ImageView) findViewById(R.id.image_viewRecap);
        imageRecap.setImageDrawable(Drawable.createFromPath(model.getPath_image()));
        //Log.d("path : ",model.getPath_image());
        typeProb = (TextView) findViewById(R.id.textTypeProb);
        dateProb = (TextView) findViewById(R.id.dateOfProb);
        versPrincipale=(Button)findViewById(R.id.brecapToMenuPrincipale);
        versPrincipale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Recapitulatif.this,MenuPrincipal.class);
                startActivity(intent);
            }
        });
        descriptionProb = (TextView) findViewById(R.id.descriptionProbleme);

        typeProb.setText("Type de Problème :"+model.getTypeProbleme().toString());
        dateProb.setText("Date :"+ new Date().toString());
        descriptionProb.setText("Description: "+model.getDescription().toString());


        //Création de l'objet a envoyer
        AuthData authData = ref.getAuth();             //Récuperation des infos de connection
        Map<String, Object> userProb = new HashMap<String, Object>();                                       //Construction de l'objet à stocker
        userProb.put("createdAt", ServerValue.TIMESTAMP);
        userProb.put("description", model.getDescription());
        userProb.put("img", "data:image/jpeg;base64," + model.getImage());
        userProb.put("state_id", "-KE4nFBeR3vJxOQBQo2z");// le code correspond à l'etat de transmission: l'etat initial du problème


        //Type du probleme
        switch(model.getTypeProbleme().getNom())
        {
            case "Nid de poule":
                userProb.put("type_id", "-KE4q3sSZKy8IfFU8olR");
                break;
            case "Accident":
                userProb.put("type_id", "-KE4oXJehkLBFgDbiB0C");
                break;
            case "Eclairage":
                userProb.put("type_id", "-KE4pz4aoR1x1P1k5Siz");
                break;
            case "Déchets":
                userProb.put("type_id", "-KE4q7dKCH1lP62RTGsf");
                break;
            case "Véhicule abandonné":
                userProb.put("type_id", "-KE4pL8DFMQ7ZFpAu_li");
                break;
            case "Incendie":
                userProb.put("type_id", "-KE4q0rMkbSCDk4tWaij");
                break;
            case "Accessibilité":
                userProb.put("type_id", "-KE4pUJgCjA5gkgCe0YZ");
                break;
            case "Animal":
                userProb.put("type_id", "-KE4oi2IboiMySwImLgJ");
                break;
            case "Transports":
                userProb.put("type_id", "-KE4ooS8_PjkShOJkpGV");
                break;
            case "Chute d arbre":
                userProb.put("type_id", "-KE4qAeawRSWt8tyOc_q");
                break;
            case "Autre":
                userProb.put("type_id", "-KE4q5WnPV-sp0YuxpT8");
                break;
            default:
                userProb.put("type_id", "-KE4q5WnPV-sp0YuxpT8");
                break;

        }

        userProb.put("updatedAt", ServerValue.TIMESTAMP);
        userProb.put("user_id", authData.getUid());


        // création de l'ID du problème
        Firebase Problems = ref.child("problems/");
        Firebase newProb = Problems.push();
        final String ProbId = newProb.getKey();
        newProb.setValue(userProb);

        //Mise a jours de l'utilisateur
        Firebase usersUpdate = ref.child("users/" + authData.getUid() + "/problems/");
        usersUpdate.child("" + ProbId).setValue("True");


        //Mise a jour de Locations
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Firebase locationMaj=ref.child("locations/problems");
        final GeoFire geoFire = new GeoFire(locationMaj);

        //geoFire.setLocation(ProbId,g);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                while(model.getLongitude()==0.0 && model.getLongitude()==0.0) {
                    model.setLatitude(location.getLatitude());
                    model.setLongitude(location.getLongitude());
                    Log.d("LatitudeList=", ("" + model.getLatitude()));
                    Log.d("LongitudeList=", ("" + model.getLongitude()));
                    GeoLocation g=new GeoLocation(model.getLatitude(),model.getLongitude());
                    geoFire.setLocation(ProbId,g);


                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);







    }
}