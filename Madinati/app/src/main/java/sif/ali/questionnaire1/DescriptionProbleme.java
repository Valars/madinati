package sif.ali.questionnaire1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sif.ali.questionnaire1.Model.Model;
import sif.ali.questionnaire1.Model.Type;

public class DescriptionProbleme extends AppCompatActivity {

    EditText commentaire;
    Button envoyerProb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_probleme);

        Intent intent=getIntent();
        final Model model=(Model)intent.getSerializableExtra("Model");
        Log.d("3_Receive",model.toString());
        //final Type type=(Type)intent.getSerializableExtra("Type");

        commentaire=(EditText)findViewById(R.id.commentaireProblemeTxt);
        envoyerProb=(Button)findViewById(R.id.envoyerProbleme);
        envoyerProb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String com=commentaire.getText().toString();
                model.setDescription(com);
                Intent intent1=new Intent(DescriptionProbleme.this,MenuChoix.class);
                Log.d("3_Send", model.toString());
                intent1.putExtra("Model",model);
                //intent1.putExtra("Type",type);
                startActivity(intent1);
            }
        });
    }
}
