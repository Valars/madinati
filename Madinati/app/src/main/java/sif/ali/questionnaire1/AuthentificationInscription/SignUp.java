package sif.ali.questionnaire1.AuthentificationInscription;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.Map;

import sif.ali.questionnaire1.AppProperties;
import sif.ali.questionnaire1.Model.Tools;
import sif.ali.questionnaire1.R;

public class SignUp extends AppCompatActivity implements View.OnClickListener, Firebase.ResultHandler{

    ImageButton bRegister;
    EditText etName, etEmail, etPassword;
    Firebase firebase = new Firebase(AppProperties.ADRESS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bRegister=(ImageButton)findViewById(R.id.bRegister);
        etName=(EditText)findViewById(R.id.etName);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bRegister:
                Log.d("[LOGS]", "Click on register");

                firebase.createUser(etEmail.getText().toString(), etPassword.getText().toString(), this);
                break;
        }
    }

    @Override
    public void onSuccess() {
        Map<String, Object> userData = Tools.getPasswordUserDefault(etName.getText().toString(), etEmail.getText().toString());                                       //Construction de l'objet à stocker
        Log.d("[LOGS]", "Création d'un utilisateur réussie");
        firebase.authWithPassword(etEmail.getText().toString(), etPassword.getText().toString(), new FBAuthResultHandler(this, userData));
    }

    @Override
    public void onError(FirebaseError err) {
        Log.d("[LOGS]Err create user", err.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}