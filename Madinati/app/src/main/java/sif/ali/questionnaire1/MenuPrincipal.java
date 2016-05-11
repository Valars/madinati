package sif.ali.questionnaire1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ImageButton;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import sif.ali.questionnaire1.Maps.Carte;
import sif.ali.questionnaire1.MesProblems.MesProblemes;
import sif.ali.questionnaire1.Model.Model;
import sif.ali.questionnaire1.Model.Type;
import sif.ali.questionnaire1.TypeProblem.TypeProbleme;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener{
    ImageButton FormulaireProbButton;
    ImageButton carte;
    ImageButton myProblems;
    ImageButton blogOut;
    ImageButton bsettings;
    Firebase firebase = new Firebase(AppProperties.ADRESS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*********************/
        AuthData authData = firebase.getAuth();             //Récuperation des infos de connection
        if(authData != null)
        {
            Log.d("[LOGS]MMP userData", authData.toString());
        }
        else Log.d("[LOGS]MP userData", "null");
        /*********************/
        setContentView(R.layout.activity_menu_principal);
        bsettings=(ImageButton) findViewById(R.id.bSettings);
        bsettings.setOnClickListener(this);
        FormulaireProbButton=(ImageButton)findViewById(R.id.Formulairebutton);
        myProblems=(ImageButton)findViewById(R.id.imageButton3);
        myProblems.setOnClickListener(this);
        blogOut=(ImageButton)findViewById(R.id.bLogOut);
        blogOut.setOnClickListener(this);
        FormulaireProbButton.setOnClickListener(this);
        carte=(ImageButton)findViewById(R.id.btnProblemsInCarte);
        carte.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Formulairebutton:

                Intent intent=new Intent(this,TypeProbleme.class);
                Model model=new Model();
                Type type=new Type("");
                intent.putExtra("Type",type);
                intent.putExtra("Model",model);
                Log.d("1_Before_send",model.toString());
                startActivity(intent);

                break;
            case R.id.btnProblemsInCarte:
                Intent intent2=new Intent(MenuPrincipal.this,Carte.class);
                startActivity(intent2);
                break;

            case R.id.bSettings:
                Intent intent5=new Intent(MenuPrincipal.this,Setting.class);
                startActivity(intent5);
                break;

            case R.id.imageButton3:
                Intent intent3=new Intent(this,MesProblemes.class);
                startActivity(intent3);
                break;
            case R.id.bLogOut:
                firebase.unauth();
                Intent intent4=new Intent(MenuPrincipal.this,FirstActivity.class);
                startActivity(intent4);
                break;
        }

    }
}
