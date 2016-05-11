package sif.ali.questionnaire1.TypeProblem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sif.ali.questionnaire1.MesProblems.MesProblemes;
import sif.ali.questionnaire1.R;
import sif.ali.questionnaire1.TypeProblem.TypeProbleme;


/**
 * Created by ali on 27/04/2016.
 */
public class TypeProbAdapter extends ArrayAdapter{

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public TypeProbAdapter(TypeProbleme mesProblemes, String[] prgmNameList, int[] prgmImages) {
        super(mesProblemes.getBaseContext(),0);

        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mesProblemes;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.singl_row_type_prob, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textViewTypeProblem);
        holder.img=(ImageView) rowView.findViewById(R.id.imageViewTypeProblem);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
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
