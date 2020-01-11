package com.example.mostafa.projecte_commerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class shopingcart extends AppCompatActivity {
Customers c;
    EditText total,quantity,pdname1,pdname2;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopingcart);
      c=new Customers(getApplicationContext());
       listView=(ListView)findViewById(R.id.listproduct);
        Button submit=(Button)findViewById(R.id.submit);
        quantity=(EditText)findViewById(R.id.quantitynum);
        pdname1=(EditText)findViewById(R.id.deletename);
        pdname2=(EditText)findViewById(R.id.nameofupdatedelemment);
         Button delete=(Button)findViewById(R.id.btndelete);
        Button update=(Button)findViewById(R.id.btnupdate);
        total=(EditText)findViewById(R.id.toalmoney);
        total.setText(c.totalmoney());
         showdata();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a=pdname1.getText().toString();
               int s= c.deleteproduct(a);
              if(s>0) {
                  Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                  total.setText(c.totalmoney());
              }
                else
                  Toast.makeText(getApplicationContext(),"not deleted",Toast.LENGTH_SHORT).show();

                showdata();
            }
             });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=pdname2.getText().toString();
                String b=quantity.getText().toString();
                if(c.updateorder(a,b)==true)
                {
                    showdata();
                    total.setText(c.totalmoney());
                }
                else
                    Toast.makeText(getApplicationContext(),"there is no update",Toast.LENGTH_SHORT).show();

            }
        });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   // c.clearallfromdatabase();
                        Intent i=new Intent(getApplicationContext(),Last.class);
                        String s= (String) getIntent().getExtras().get("get_id2");
                        i.putExtra("get_id3",s);
                        startActivity(i);


                    }
                });
    }
    public void showdata()
    {
        ArrayList<String>arr=c.returnsavingorder();
        ArrayAdapter   arrayAdapter=new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(arrayAdapter);

    }


}
