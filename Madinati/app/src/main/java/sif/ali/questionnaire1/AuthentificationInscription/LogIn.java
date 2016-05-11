package sif.ali.questionnaire1.AuthentificationInscription;

import android.content.Context;
import android.util.Log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;

import sif.ali.questionnaire1.AppProperties;
import sif.ali.questionnaire1.MenuPrincipal;
import sif.ali.questionnaire1.R;

public class LogIn extends AppCompatActivity implements View.OnClickListener{

    ImageButton bLogin;
    EditText userName, password;
    TextView tvregisterLink;
    Firebase firebase;
    private Context lecontext;
    TextView bAnonymous;
    FBAuthResultHandlerAno onAuth;
    ProgressBar lConnection;
    int test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*******************/
        Firebase.setAndroidContext(this);
        onAuth = new FBAuthResultHandlerAno(this, MenuPrincipal.class);
        lecontext=this;


              //On ajoute Firebase
        firebase = new Firebase(AppProperties.ADRESS);               //on crée une ref vers notre serveur
        //firebase.unauth();  //A virer, juste pour déco l'utilisateur pour les tests de co
        //firebase.addAuthStateListener(new FBAuthStatListener(this, MenuPrincipal.class));   //Listener sur un evenement de déco ou connection, aussi levé si on lance l'appli et qu'on est pas co

        /*******************/
        setContentView(R.layout.activity_log);
        bLogin=(ImageButton)findViewById(R.id.bLogin);
        password=(EditText)findViewById(R.id.logPassword);
        userName=(EditText)findViewById(R.id.logUserName);
        bLogin.setOnClickListener(this);
        tvregisterLink=(TextView)findViewById(R.id.tvRegisterLink);
        bAnonymous=(TextView) findViewById(R.id.bAnonymous);
        tvregisterLink.setOnClickListener(this);
        bAnonymous.setOnClickListener(this);
        lConnection = (ProgressBar) findViewById(R.id.lConnection);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bLogin:
                //final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                final String un = userName.getText().toString(),
                        pa = password.getText().toString();
                firebase.authWithPassword(un, pa, new FBAuthResultHandler(this, MenuPrincipal.class));
                break;
            case R.id.bAnonymous:
                lConnection.setVisibility(View.VISIBLE);
                firebase.authAnonymously(onAuth);
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, SignUp.class));
                break;

        }
    }
}
