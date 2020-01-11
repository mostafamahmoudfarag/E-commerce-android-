package com.example.mostafa.projecte_commerce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mostafa on 12/18/2018.
 */

public class myadapter extends BaseAdapter implements Filterable
{
    Context c;
   ArrayList<String> namep=new ArrayList();
    ArrayList<String> salaryp=new ArrayList();
    ArrayList<String> quantityp=new ArrayList();

    customfilter cs;
    ArrayList<listitem>originalarray,temparray;


    public myadapter(Context c,ArrayList<listitem>originalarray)
    {
        this.c=c;
        this.originalarray=originalarray;
        this.temparray=originalarray;
    }

    @Override
    public Object getItem(int position) {
        return originalarray.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.item,null);
      final TextView name=(TextView)row.findViewById(R.id.name);
        final TextView salary = (TextView) row.findViewById(R.id.sal);
         final  EditText num=(EditText)row.findViewById(R.id.number);
        Button buy=(Button)row.findViewById(R.id.btn_buy);
        ImageView img=(ImageView)row.findViewById(R.id.image);
        name.setText(originalarray.get(position).name);
        salary.setText(originalarray.get(position).salary);
        img.setImageResource(originalarray.get(position).image);
        num.setText(originalarray.get(position).num);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(!"".equals(num.getText().toString())) {
    namep.add(name.getText().toString());
    salaryp.add(salary.getText().toString());
    quantityp.add(num.getText().toString());


}

            }
        });

        return row;
    }

    @Override
    public int getCount() {
        return originalarray.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
if(cs==null)
{
    cs=new customfilter();
}

        return cs;
    }
    class customfilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint!=null&&constraint.length()>0) {
                constraint = constraint.toString().toUpperCase();

                ArrayList<listitem> filters = new ArrayList<>();
                for (int i = 0; i < temparray.size(); i++) {
                    if (temparray.get(i).getName().toUpperCase().contains(constraint)) {
                        listitem listitem = new listitem(temparray.get(i).getImage(), temparray.get(i).name, temparray.get(i).getSalary()
                                , temparray.get(i).getNum());
                        filters.add(listitem);
                    }
                }
                results.count=filters.size();
                results.values=filters;

            }
            else
            {
                results.count=temparray.size();
                results.values=temparray;
            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
originalarray=(ArrayList<listitem>)results.values;
            notifyDataSetChanged();
        }
    }
}
