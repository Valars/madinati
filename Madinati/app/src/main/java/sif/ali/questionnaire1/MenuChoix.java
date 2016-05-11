package sif.ali.questionnaire1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import sif.ali.questionnaire1.Model.Model;

public class MenuChoix extends AppCompatActivity{

    ImageButton bEnvoyerProb;
    ImageButton bAddDesc;
    ImageButton bAddPiture;
   //ImageButton bAddPic;


    //ImageButton bDescription,bSendProb,bAddPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choix);

        Intent intent=getIntent();
        final Model model=(Model)intent.getSerializableExtra("Model");
        Log.d("MdlMenuChoi", model.toString());


        bEnvoyerProb=(ImageButton)findViewById(R.id.bSendProblem);
        bAddDesc=(ImageButton)findViewById(R.id.bAddDescription);
        bAddPiture=(ImageButton)findViewById(R.id.bAddPicture);

        bAddPiture.setImageResource(R.drawable.addpicture);
        bAddDesc.setImageResource(R.drawable.adddescription);
        bEnvoyerProb.setImageResource(R.drawable.sendprob);

        bAddDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(MenuChoix.this,DescriptionProbleme.class);
                intent3.putExtra("Model",model);
                startActivity(intent3);
            }
        });
        bEnvoyerProb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(MenuChoix.this,Recapitulatif.class);
                intent2.putExtra("Model",model);
                startActivity(intent2);
            }
        });
        bAddPiture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuChoix.this,CameraActivity.class);
                intent.putExtra("Model",model);
                startActivity(intent);
            }
        });
    }
}
