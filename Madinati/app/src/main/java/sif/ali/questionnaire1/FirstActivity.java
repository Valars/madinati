package sif.ali.questionnaire1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import sif.ali.questionnaire1.AuthentificationInscription.FBAuthResultHandler;
import sif.ali.questionnaire1.AuthentificationInscription.LogIn;
import sif.ali.questionnaire1.AuthentificationInscription.SignUp;
import sif.ali.questionnaire1.AuthentificationInscription.SignUp_;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton versLogIn;
    ImageButton versSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_first);

        versLogIn=(ImageButton)findViewById(R.id.buttonVersLogIn);
        versSingUp=(ImageButton) findViewById(R.id.buttonVersSingUp);

        versSingUp.setOnClickListener(this);
        versLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonVersLogIn:
                startActivity(new Intent(this, LogIn.class));
                break;
            case R.id.buttonVersSingUp:
                startActivity(new Intent(this, SignUp.class));
                break;

        }

    }
}
