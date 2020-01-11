package com.example.mostafa.projecte_commerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    ListView L;
    ArrayAdapter arr;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
          L=(ListView)findViewById(R.id.list);
          ArrayList<String> list=new ArrayList();
          list.add("Mobiles");
          list.add("cars");
          list.add("clothes");
          list.add("accessories");
          list.add("laptops");

         arr=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);
          L.setAdapter(arr);

         L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 if(position==0)
                  {
                      Intent i=new Intent(getApplicationContext(),Products.class);
                      String d= (String) getIntent().getExtras().get("get_id");
                      i.putExtra("get_id1",d);
                     startActivity(i);
                 }
             }
         });

      }
}
