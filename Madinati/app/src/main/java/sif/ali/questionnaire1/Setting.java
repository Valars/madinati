package sif.ali.questionnaire1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import sif.ali.questionnaire1.AuthentificationInscription.FBAuthResultHandler;

public class Setting extends AppCompatActivity implements View.OnClickListener {

    //Change Password
    EditText email;
    EditText oldPassword;
    EditText newPassword;
    ImageButton valider;


    //Change Email
    EditText oldEmail;
    EditText newEmail;
    EditText password;
    ImageButton bChangeEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        email=(EditText)findViewById(R.id.bSettingEmail);
        oldPassword=(EditText)findViewById(R.id.bSettingOldPassword);
        newPassword=(EditText)findViewById(R.id.bSettingNewPassword);
        valider=(ImageButton)findViewById(R.id.bValiderSetting);
        valider.setOnClickListener(this);


        oldEmail=(EditText)findViewById(R.id.bOldEmailChangeEmail);
        newEmail=(EditText)findViewById(R.id.bNewEmailChangeEmail);
        password=(EditText)findViewById(R.id.bPasswordChangeEmail);
        bChangeEmail=(ImageButton)findViewById(R.id.bValiderChangeEmail);
        bChangeEmail.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bValiderSetting:
                final Firebase ref = new Firebase(AppProperties.ADRESS);

                ref.changePassword(email.getText().toString(), oldPassword.getText().toString(), newPassword.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Log.d("Resultat:", "Succes Change Password");

                        Toast.makeText(getApplicationContext(), "Succes Change Password", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(Setting.this, MenuPrincipal.class);
                        startActivity(intent1);

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Log.d("Resultat:", "Echec Change Password");
                        Toast.makeText(getApplicationContext(), "Iinvalid Change Password", Toast.LENGTH_SHORT).show();
                    }
                });

                break;

            case R.id.bValiderChangeEmail:

                final Firebase ref2 = new Firebase(AppProperties.ADRESS);


                ref2.changeEmail(oldEmail.getText().toString(), password.getText().toString(), newEmail.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Log.d("Resultat:", "Succes Change Email");
                        Toast.makeText(getApplicationContext(), "Succes Change Email\n Your new Email is: "+newEmail.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Setting.this, MenuPrincipal.class);
                        startActivity(intent2);


                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Log.d("Resultat:", "Invalid Change Email");

                        Toast.makeText(getApplicationContext(), "Invalid Change Password", Toast.LENGTH_SHORT).show();


                    }
                });

                break;
        }

    }
}
