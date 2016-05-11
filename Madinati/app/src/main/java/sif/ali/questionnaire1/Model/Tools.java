package sif.ali.questionnaire1.Model;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by romaing on 27/04/2016.
 */
public final class Tools {

    static public HashMap<String, Object> getPasswordUserDefault(String pseudonyme,String email){
        String pseudo;
        if(pseudonyme == null) pseudo = "null";
        else pseudo = pseudonyme;
        HashMap<String, Object> userStruct = new HashMap<String, Object>();
        userStruct.put("createdAt", ServerValue.TIMESTAMP);
        userStruct.put("updatedAt", ServerValue.TIMESTAMP);
        userStruct.put("lastConnection", ServerValue.TIMESTAMP);
        userStruct.put("role", 10);
        userStruct.put("deprecated", false);
        userStruct.put("provider", "password");
        userStruct.put("pseudonyme", pseudo);
        userStruct.put("email", email);
        return userStruct;
    }
    static public HashMap<String, Object> getAnonymousUserDefault(){
        HashMap<String, Object> userStruct = new HashMap<String, Object>();
        userStruct.put("createdAt", ServerValue.TIMESTAMP);
        userStruct.put("updatedAt", ServerValue.TIMESTAMP);
        userStruct.put("lastConnection", ServerValue.TIMESTAMP);
        userStruct.put("pseudonyme", "Anonymous");
        userStruct.put("deprecated", false);
        userStruct.put("provider", "anonymous");
        return userStruct;
    }
    static public String getImei(AppCompatActivity act){
        return Settings.Secure.getString(act.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    Map<String, Object> userData = new HashMap<String, Object>();                                       //Construction de l'objet à stocker

}
