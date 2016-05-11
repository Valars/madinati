package sif.ali.questionnaire1.AuthentificationInscription;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import sif.ali.questionnaire1.MenuPrincipal;
import sif.ali.questionnaire1.Model.Tools;
import sif.ali.questionnaire1.R;

import static android.content.Context.CONTEXT_INCLUDE_CODE;

/**
 * Created by romaing on 30/03/2016.
 * "Callback" à utiliser pour les connection d'utilisateur
 */
public class FBAuthResultHandler implements Firebase.AuthResultHandler{

    AppCompatActivity srcAct = null;
    Class<?> dstAct = null;
    Map<String,Object> newUserData = null;
    String userId = null;
    Firebase firebase = new Firebase(AppProperties.ADRESS);

    public FBAuthResultHandler(AppCompatActivity srcAct, Class<?> dstAct){
        this.srcAct = srcAct;
        this.dstAct = dstAct;
    }

    public FBAuthResultHandler(AppCompatActivity srcAct, Map<String,Object> newUserData){
        this.srcAct = srcAct;
        this.newUserData = newUserData;
    }

    @Override
    public void onAuthenticated(final AuthData authData) {
        //Utilisateur connecté, on met son lastConnection à jour
        if(authData.getProvider().intern() == "password") userId = authData.getUid();
        else if(authData.getProvider().intern() == "anonymous") userId = Tools.getImei(srcAct);

        Toast.makeText(srcAct, "Connected", Toast.LENGTH_LONG).show();
        Log.d("[LOGS]Get on", "users/"+userId);
        firebase.child("users/"+userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){  //Si l'utilisateur n'est pas nouveau
                            Map<String, Object> updatedVal = new HashMap<String, Object>();
                            updatedVal.put("lastConnection", ServerValue.TIMESTAMP);
                            firebase.child("users/"+userId).updateChildren(updatedVal,
                                    new Firebase.CompletionListener() {
                                        @Override
                                        public void onComplete(FirebaseError err, Firebase firebase) {
                                            srcAct.findViewById(R.id.lConnection).setVisibility(View.INVISIBLE);
                                            if(srcAct!=null && dstAct!=null) srcAct.startActivity(new Intent(srcAct, dstAct));
                                        }
                                    });
                        }
                        else setNewUserData(authData);       //Si nouvel utilisateur
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
        View pb = srcAct.findViewById(R.id.lConnection);
        if(pb!=null) pb.setVisibility(View.INVISIBLE);
        switch (err.getCode()){
            case FirebaseError.USER_DOES_NOT_EXIST:
                Toast.makeText(srcAct, "User does not exist",Toast.LENGTH_LONG).show();
                break;
            case FirebaseError.INVALID_PASSWORD:
                Toast.makeText(srcAct, "Wrong password",Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void setNewUserData(final AuthData authData){
        if(authData.getProvider().intern() == "anonymous") newUserData = Tools.getAnonymousUserDefault();

        Log.d("[LOGS]", "New User Data "+newUserData.toString());
        if(newUserData != null) {
            firebase.child("users/" + userId).setValue(newUserData,
                new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError err, Firebase firebase) {
                        if (err != null) Toast.makeText(srcAct, "Account creation failed",Toast.LENGTH_LONG).show();
                        else Toast.makeText(srcAct, "Account created", Toast.LENGTH_LONG).show();
                        if(err != null) Log.d("[LOGS]", "Error "+err.toString());
                        else Log.d("[LOGS]", "Account creation succed ");
                        View pb = srcAct.findViewById(R.id.lConnection);
                        if(pb!=null) pb.setVisibility(View.INVISIBLE);
                        if (srcAct != null && dstAct != null && err == null) srcAct.startActivity(new Intent(srcAct, dstAct));
                        else srcAct.startActivity(new Intent(srcAct, MenuPrincipal.class));
                    }
                }
            );
        }
        else firebase.unauth();
    }
}
