package sif.ali.questionnaire1.AuthentificationInscription;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import sif.ali.questionnaire1.AppProperties;

import static android.content.Context.CONTEXT_INCLUDE_CODE;

/**
 * Created by romaing on 30/03/2016.
 * "Callback" à utiliser pour les connection d'utilisateur
 */
public class FBAuthResultHandler implements Firebase.AuthResultHandler{

    AppCompatActivity srcAct = null;
    Class<?> dstAct = null;
    Map<String,Object> newUserData = null;

    public FBAuthResultHandler(AppCompatActivity srcAct, Class<?> dstAct){
        this.srcAct = srcAct;
        this.dstAct = dstAct;
    }

    public FBAuthResultHandler(AppCompatActivity srcAct, Map<String,Object> newUserData){
        this.srcAct = srcAct;
        this.newUserData = newUserData;
    }

    public  FBAuthResultHandler(Boolean newUser){}

    @Override
    public void onAuthenticated(final AuthData authData) {
        //Utilisateur connecté, on met son lastConnection à jour
        final Firebase firebase = new Firebase(AppProperties.ADRESS).child("users/"+authData.getUid());
        Log.d("[LOGS]Connection succed", authData.toString());        //Si la co réussie, on passe à l'activité suivante

        firebase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){  //Si l'utilisateur n'est pas nouveau
                            Log.d("[LOGS]Get user info","OK : "+dataSnapshot.getValue().toString());
                            Map<String, Object> updatedVal = new HashMap<String, Object>();
                            updatedVal.put("lastConnection", ServerValue.TIMESTAMP);
                            firebase.updateChildren(updatedVal,
                                    new Firebase.CompletionListener() {
                                        @Override
                                        public void onComplete(FirebaseError err, Firebase firebase) {
                                            if(err!=null) Log.d("[LOGS]Update user info","Error : "+err.toString());
                                            else Log.d("[LOGS]Update user info", "OK");
                                            if(srcAct!=null && dstAct!=null) srcAct.startActivity(new Intent(srcAct, dstAct));
                                        }
                                    });
                        }
                        else setNewUserData(authData); ;       //Si nouvel utilisateur


                    }
                    @Override
                    public void onCancelled(FirebaseError err) {
                        Log.d("[LOGS]Get user info", "Err : "+err);
                        setNewUserData(authData);
                    }
                }
        );
    }

    @Override
    public void onAuthenticationError(FirebaseError err) {
        Log.d("[LOGS]Connection failed", err.toString());

        switch (err.getCode()){
            case FirebaseError.USER_DOES_NOT_EXIST:
                //Afficher un message si l'utilisateur n'existe pas
                //Toast.makeText(getApplicationContext(), "Position: " + position +
                //      "  - Item: " + typeProb[position], Toast.LENGTH_SHORT).show();
                Toast.makeText(srcAct,"User not exist",Toast.LENGTH_LONG).show();
                break;
            case FirebaseError.INVALID_PASSWORD:
                //Afficher un message si le mot de passe est incorrecte
                Toast.makeText(srcAct," Invalid Password",Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void setNewUserData(final AuthData authData){
        Log.d("[LOGS]Get user info","Rien");
        Firebase firebase = new Firebase(AppProperties.ADRESS).child("users/"+authData.getUid());

        if(newUserData != null){
            firebase.setValue(newUserData,
                    new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError err, Firebase firebase) {
                            if(err!=null) Log.d("[LOGS]Add user info","Error : "+err.toString());
                            else Log.d("[LOGS]Add user info", "OK");
                            if(srcAct!=null && dstAct!=null) srcAct.startActivity(new Intent(srcAct, dstAct));
                        }
                    }
            );
        }
        else firebase.unauth();
    }
}
