package sif.ali.questionnaire1.AuthentificationInscription;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import sif.ali.questionnaire1.AuthentificationInscription.SignUp;
import sif.ali.questionnaire1.MenuPrincipal;

/**
 * Created by romaing on 30/03/2016.
 * "Callback" à utiliser quand avec createUser
 */
public class FBRegisterResultHandler implements Firebase.ResultHandler {

    SignUp signUpApp;


    public FBRegisterResultHandler(SignUp signUpApp){
        this.signUpApp = signUpApp;
    }
    @Override
    public void onSuccess() {
        Log.d("[LOGS]", "Création d'un utilisateur réussie");
        signUpApp.sendUserInfos();
        Toast.makeText(signUpApp, "your account is successfully created", Toast.LENGTH_LONG).show();
        //Intent intent= new Intent(signUpApp, MenuPrincipal.class);
        signUpApp.startActivity(new Intent(signUpApp, MenuPrincipal.class));
    }

    @Override
    public void onError(FirebaseError err) {
        Toast.makeText(signUpApp, err.toString(), Toast.LENGTH_LONG).show();
    }
}
