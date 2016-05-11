package sif.ali.questionnaire1.AuthentificationInscription;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.Map;

import sif.ali.questionnaire1.AppProperties;
import sif.ali.questionnaire1.R;

public class SignUp_ extends AppCompatActivity {

    ImageButton bRegister;
    EditText etName, etEmail, etPassword;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);

        bRegister=(ImageButton)findViewById(R.id.bRegister1);
        etName=(EditText)findViewById(R.id.etName1);
        etEmail=(EditText)findViewById(R.id.etEmail1);
        etPassword=(EditText)findViewById(R.id.etPassword1);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase firebase = new Firebase(AppProperties.ADRESS);
                //firebase.createUser(etEmail.getText().toString(), etPassword.getText().toString(), new FBRegisterResultHandler(SignUp_.this));
            }
        });

    }

    public void sendUserInfos(){
        //utilisateur crée, on ajoute ses infos dans la base
        Map<String, Object> userData = new HashMap<String, Object>();                                       //Construction de l'objet à stocker
        userData.put("createdAt", ServerValue.TIMESTAMP);
        userData.put("updatedAt", ServerValue.TIMESTAMP);
        userData.put("lastConnection", ServerValue.TIMESTAMP);
        userData.put("role", 10);
        userData.put("pseudonyme", etName.getText().toString());
        userData.put("email", etEmail.getText().toString());
        userData.put("deprecated", false);
        userData.put("provider", "password");

        Log.d("D/[LOGS]", "sendUserInfos: " + userData.toString());

        firebase.authWithPassword(etEmail.getText().toString(), etPassword.getText().toString(), new FBAuthResultHandler(this, userData));     //Connection du nouvel utilisateur
    }
}
