package sif.ali.questionnaire1.AuthentificationInscription;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.Map;

import sif.ali.questionnaire1.AppProperties;
import sif.ali.questionnaire1.R;

public class SignUp extends AppCompatActivity{

    ImageButton bRegister;
    EditText etName, etEmail, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);

        bRegister=(ImageButton)findViewById(R.id.bRegister);
        etName=(EditText)findViewById(R.id.etName);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase firebase = new Firebase(AppProperties.ADRESS);
                firebase.createUser(etEmail.getText().toString(), etPassword.getText().toString(), new FBRegisterResultHandler(SignUp.this));
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

        Log.d("D/[LOGS]", "sendUserInfos: "+userData.toString());
        Firebase firebase = new Firebase(AppProperties.ADRESS);
        firebase.authWithPassword(etEmail.getText().toString(), etPassword.getText().toString(), new FBAuthResultHandler(this, userData));     //Connection du nouvel utilisateur
    }
}