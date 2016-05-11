package sif.ali.questionnaire1.AuthentificationInscription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import sif.ali.questionnaire1.AuthentificationInscription.LogIn;

/**
 * Created by romaing on 30/03/2016.
 * Ecoute les evenements de déconnection et connection
 */
public class FBAuthStatListener implements  Firebase.AuthStateListener{

    AppCompatActivity srcAct = null;
    Class<?> dstAct = null;

    public FBAuthStatListener(AppCompatActivity srcAct, Class<?> dstAct){
        this.srcAct = srcAct;
        this.dstAct = dstAct;
    }

    public FBAuthStatListener(){}

    @Override
    public void onAuthStateChanged(AuthData authData) {
        if(authData != null){                               //Cas ou il y a eu connexion
            //Log.d("[LOGS]User connected",authData.toString());
            //Firebase firebase= new Firebase(AppProperties.ADRESS);
            //firebase.unauth();
            //if(srcAct!=null && dstAct!=null) srcAct.startActivity(new Intent(srcAct, dstAct));
        }
        else{                                               //Cas ou il y a déco ou pas de connection
            Log.d("[LOGS]", "user deconnected");
            if(!LogIn.class.isInstance(srcAct)) srcAct.startActivity(new Intent(srcAct, LogIn.class));  //Si déco on retourne au menu de co
        }
    }
}
