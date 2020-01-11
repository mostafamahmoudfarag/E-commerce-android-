package com.example.mostafa.projecte_commerce;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Products extends AppCompatActivity implements TextWatcher{
    int mobile[] = {R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e,
            R.drawable.f, R.drawable.g, R.drawable.h,
            R.drawable.i,
            R.drawable.j};
    myadapter myadapt;
Customers c;
    EditText S;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        c=new Customers(getApplicationContext());
        Button add = (Button) findViewById(R.id.confirm);
        Button record=(Button)findViewById(R.id.btnsearchbyvoice);
        S=(EditText)findViewById(R.id.searchtext);
        S.addTextChangedListener(this);
        ListView lis = (ListView) findViewById(R.id.List);
        String[] name = getResources().getStringArray(R.array.name);
        String[] salary = getResources().getStringArray((R.array.sal));
        ArrayList<listitem> pro = new ArrayList<listitem>();
        for (int i = 0; i < name.length; i++) {
            pro.add(new listitem(mobile[i], name[i], salary[i], ""));
        }
        //listadapter ad = new listadapter(pro);
        //lis.setAdapter(ad);
        myadapt=new myadapter(this,pro);
        lis.setAdapter(myadapt);


add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        for(int j=0;j<myadapt.namep.size();j++)
        {
            String a=myadapt.namep.get(j).toString();
            String b=myadapt.salaryp.get(j).toString();
            String d=myadapt.quantityp.get(j).toString();

            if(c.savingorder(a,b,d)==true)
            {
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();

            }
        }
        myadapt.namep.clear();
        myadapt.salaryp.clear();
        myadapt.quantityp.clear();

        Intent i=new Intent(getApplicationContext(),shopingcart.class);
        String s= (String) getIntent().getExtras().get("get_id1");
        i.putExtra("get_id2",s);
        startActivity(i);

    }
});

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                try{
                    startActivityForResult(intent,200);


                }
                catch (ActivityNotFoundException a)
                {
Toast.makeText(getApplicationContext(),"Intent problm",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200)
       if(resultCode==RESULT_OK && data!=null)
       {
           ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
           S.setText(result.get(0));

       }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
this.myadapt.getFilter().filter(s);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}


