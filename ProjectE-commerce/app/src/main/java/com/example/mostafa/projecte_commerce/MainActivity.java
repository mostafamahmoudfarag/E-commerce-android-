package com.example.mostafa.projecte_commerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Customers user;
    EditText E1,E2;
    RadioButton R1,R2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=new Customers(getApplicationContext());
        Button sign=(Button)findViewById(R.id.signup);
        Button Login=(Button)findViewById(R.id.login);
        E1=(EditText)findViewById(R.id.username);
        E2=(EditText)findViewById(R.id.passw);
        R1=(RadioButton)findViewById(R.id.radioButton5);
        R2=(RadioButton)findViewById(R.id.radioButton6);


        show();
        user.clearallfromdatabase();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Registration_Form.class);
                startActivity(i);
            }
        });

        
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = E1.getText().toString();
                String b = E2.getText().toString();

                if(R1.isChecked())
                {

                    if(user.rememberme(E1.getText().toString(),E2.getText().toString())==true)
                    {
                        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"already saved",Toast.LENGTH_LONG).show();
                }
                if(R2.isChecked())
                {
               if(user.deleteemlement(a)==1)
                   Toast.makeText(getApplication(),"forget",Toast.LENGTH_LONG).show();
                else
                   Toast.makeText(getApplication(),"not saved to forget it",Toast.LENGTH_LONG).show();
                }
                if (user.log(a, b) == true)
                {
                    Intent i = new Intent(getApplicationContext(), Categories.class);
String d=user.get_id(E1.getText().toString());
                    i.putExtra("get_id",d);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public void show()
    {
        user.retrivedata();
        String a=user.U;
        String b=user.P;
        E1.setText(a);
        E2.setText(b);

    }

}
