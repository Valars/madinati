package sif.ali.questionnaire1.TypeProblem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import sif.ali.questionnaire1.CameraActivity;
import sif.ali.questionnaire1.MenuChoix;
import sif.ali.questionnaire1.Model.Model;
import sif.ali.questionnaire1.R;

public class TypeProbleme extends AppCompatActivity {
    String[] typeProb={"Nid de poule","Accident","Eclairage","Animal","Chute d'arbre","Accessibilité","Incendie","Déchets","Transports","Véhicule abandonné","Autre"};
    int[] images={R.drawable.a100,R.drawable.a200,R.drawable.a300,R.drawable.a400,R.drawable.a600,R.drawable.a700,R.drawable.a800,R.drawable.a900,R.drawable.a1000,R.drawable.a1200,R.drawable.a1100};
    ListView listeProb;
    TypeProbAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_probleme);
        Intent intent=getIntent();
        final Model model=(Model)intent.getSerializableExtra("Model");

        listeProb=(ListView)findViewById(R.id.listTypeProbleme);
        //TypeProbAdapter adapter=new TypeProbAdapter(this,typeProb,images);
        //listeProb.setAdapter(adapter);
        //ArrayAdapter adapter1 = new ArrayAdapter(TypeProbleme.this, android.R.layout.simple_list_item_1, typeProb);// 2eme param pour la chose que je veux afficher
        //listeProb.setAdapter(adapter1);




        listeProb=(ListView) findViewById(R.id.listTypeProbleme);
        listeProb.setAdapter(new TypeProbAdapter(this, typeProb, images));

        listeProb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                model.setTypeProbleme(typeProb[position].toString());
                //type.setNom(typeProb[position].toString());
                Toast.makeText(getApplicationContext(), "Position: " + position +
                        "  - Item: " + typeProb[position], Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(TypeProbleme.this, MenuChoix.class);
                Log.d("2_Send", model.toString());
                intent1.putExtra("Model", model);
                startActivity(intent1);
            }
        });

    }
}

