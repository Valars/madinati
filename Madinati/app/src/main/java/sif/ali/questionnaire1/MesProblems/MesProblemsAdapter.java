package sif.ali.questionnaire1.MesProblems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sif.ali.questionnaire1.R;
import sif.ali.questionnaire1.TypeProblem.TypeProbleme;

/**
 * Created by ali on 29/04/2016.
 */
public class MesProblemsAdapter extends ArrayAdapter {

    StringBuilder[] titreDuProblem;
    StringBuilder[] description;
    StringBuilder[] date;
    Context context;
    private static LayoutInflater inflater=null;
    public MesProblemsAdapter(MesProblemes baseContext, StringBuilder[] valeurType, StringBuilder[] valeurCreatedAt, StringBuilder[] valeurDescription) {
        super(baseContext.getBaseContext(),0);
        //super(mesProblemes.getBaseContext());

        this.titreDuProblem=valeurType;
        context=baseContext;
        this.description=valeurDescription;
        this.date=valeurCreatedAt;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    public int getCount() {
        // TODO Auto-generated method stub
        return titreDuProblem.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        TextView description;
        TextView date;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.singl_row_mes_problems, null);
        holder.tv=(TextView) rowView.findViewById(R.id.mesProblemsTitres);
        holder.description=(TextView) rowView.findViewById(R.id.mesProblemsDescription);
        holder.date=(TextView) rowView.findViewById(R.id.mesProblemsDate);
        holder.tv.setText(titreDuProblem[position]);
        holder.description.setText(description[position]);
        holder.date.setText(date[position]);
        /*rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });*/
        return rowView;
    }
}
