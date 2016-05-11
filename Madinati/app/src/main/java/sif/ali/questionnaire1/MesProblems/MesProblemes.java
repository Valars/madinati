package sif.ali.questionnaire1.MesProblems;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.Date;

import sif.ali.questionnaire1.AppProperties;
import sif.ali.questionnaire1.R;
import sif.ali.questionnaire1.TypeProblem.TypeProbAdapter;

public class MesProblemes extends AppCompatActivity {
    private StringBuilder[] valeurType;
    private StringBuilder[] valeurCreatedAt;
    private StringBuilder[] valeurState;
    private StringBuilder[] valeurDescription;
    private ListView listeDeMesProb;

    //Test1
    //LecteurFlux objLectFlux = new LecteurFlux();
    private Context lecontext;
    protected ProgressDialog myProgressDialog;
    final Handler uiThreadCallback= new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_problemes);

        //Variables de stocke des problemes de l'utilisateur
        lecontext=this;
        myProgressDialog = ProgressDialog.show(MesProblemes.this,"", "Chargement", true);


        final Firebase ref = new Firebase(AppProperties.ADRESS);

        final AuthData authData = ref.getAuth();
        valeurType= new StringBuilder[5];
        valeurCreatedAt=new StringBuilder[5];
        valeurState=new StringBuilder[5];
        valeurDescription=new StringBuilder[5];
        for(int i=0;i<5;i++)
        {
            valeurType[i]=new StringBuilder("");
            valeurCreatedAt[i]=new StringBuilder("");
            valeurState[i]=new StringBuilder("");
            valeurDescription[i]=new StringBuilder("");
        }





        final Firebase allProblems = ref.child("problems");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Récupération de données
                Log.d("BdittRech:",Long.toString(System.currentTimeMillis()));


                Query queryRef=allProblems.orderByChild("user_id").equalTo(authData.getUid()).limitToLast(5);

                queryRef.addChildEventListener(new ChildEventListener() {
                    int i = 4;

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Log.d("bditList:", Long.toString(System.currentTimeMillis()));
                        if (i >= 0) {
                            //extraction et conversion date
                            long da;
                            da = Long.parseLong(dataSnapshot.child("/createdAt").getValue().toString());
                            Date d = new Date(da);

                            valeurCreatedAt[i].append(d);
                            valeurDescription[i].append("Description: " + dataSnapshot.child("/description").getValue().toString());
                            valeurState[i].append(dataSnapshot.child("/state_id").getValue().toString());
                            //valeurType[i].append(dataSnapshot.child("/type_id").getValue().toString());

                            switch (dataSnapshot.child("/type_id").getValue().toString()) {
                                case "-KE4q3sSZKy8IfFU8olR":
                                    valeurType[i].append("Nid de poule");
                                    break;
                                case "-KE4oXJehkLBFgDbiB0C":
                                    valeurType[i].append("Accident");
                                    break;
                                case "-KE4pz4aoR1x1P1k5Siz":
                                    valeurType[i].append("Eclairage");
                                    break;
                                case "-KE4q7dKCH1lP62RTGsf":
                                    valeurType[i].append("Déchets");
                                    break;
                                case "-KE4pL8DFMQ7ZFpAu_li":
                                    valeurType[i].append("Véhicule abandonné");
                                    break;
                                case "-KE4q0rMkbSCDk4tWaij":
                                    valeurType[i].append("Incendie");
                                    break;
                                case "-KE4pUJgCjA5gkgCe0YZ":
                                    valeurType[i].append("Accessibilité");
                                    break;
                                case "-KE4oi2IboiMySwImLgJ":
                                    valeurType[i].append("Animal");
                                    break;
                                case "-KE4ooS8_PjkShOJkpGV":
                                    valeurType[i].append("Transports");
                                    break;
                                case "-KE4qAeawRSWt8tyOc_q":
                                    valeurType[i].append("Chute d arbre");
                                    break;
                                case "-KE4q5WnPV-sp0YuxpT8":
                                    valeurType[i].append("Autre");
                                    break;
                                default:
                                    valeurType[i].append("Autre");
                                    break;

                            }
                            i--;
                        }
                        Log.d("Salitrech:", Long.toString(System.currentTimeMillis()));
                        myProgressDialog.dismiss();

                        Log.d("BditAff:", Long.toString(System.currentTimeMillis()));
                        MesProblemsAdapter adapter1;
                        listeDeMesProb=(ListView)findViewById(R.id.listeDeMesProblemes);
                        adapter1 = new MesProblemsAdapter(MesProblemes.this,valeurType,valeurCreatedAt,valeurDescription);
                        listeDeMesProb.setAdapter(adapter1);
                        adapter1.notifyDataSetChanged();
                        Log.d("SalitAff:", Long.toString(System.currentTimeMillis()));

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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //affichage

                        if(valeurCreatedAt[0]!=null)
                        {

                            //affiche_liste();
                            //ArrayAdapter adapter1= new ArrayAdapter(MesProblemes.this,android.R.layout.simple_list_item_1, valeurCreatedAt);// 2eme param pour la chose que je veux afficher
                            //ArrayAdapter ad=new ArrayAdapter(MesProblemes.this, R.layout.singl_row_mes_problems,valeurCreatedAt);


                        }


                    }
                });
            }
        }).start();




































    }



    @Override
    protected void onPause() {
        super.onPause();

    }
}
