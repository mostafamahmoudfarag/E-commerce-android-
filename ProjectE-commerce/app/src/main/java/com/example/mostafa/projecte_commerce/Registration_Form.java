package com.example.mostafa.projecte_commerce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration_Form extends AppCompatActivity {
Customers c;
    EditText E1,E2,E3,E4;
    RadioButton R1,R2;
    String birthdate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__form);
        c=new Customers(getApplicationContext());
        E1=(EditText)findViewById(R.id.editText);
        E2=(EditText)findViewById(R.id.editText2);
        E3=(EditText)findViewById(R.id.editText3);
        E4=(EditText)findViewById(R.id.editText4);
        R1=(RadioButton)findViewById(R.id.male);
        R2=(RadioButton)findViewById(R.id.female);
        Button sub=(Button)findViewById(R.id.button);
        CalendarView C=(CalendarView)findViewById(R.id.calendarView2);
        C.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String d=String.valueOf(dayOfMonth);
                String m=String.valueOf(month+1);
                String y=String.valueOf(year);
                birthdate=d+"/"+m+"/y";

            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cuname=E1.getText().toString();
                String username=E2.getText().toString();
                String pass=E3.getText().toString();
                String job=E4.getText().toString();
               String gender="";

                if(R1.isChecked())
                    gender="male";
                else if(R2.isChecked())
                    gender="female";

                if("".equals(cuname)||"".equals(username)||"".equals(pass)||"".equals(job)||
                        "".equals(gender)||"".equals(birthdate))
                {
                    Toast.makeText(getApplicationContext(),"please write all data",Toast.LENGTH_LONG).show();
                }
                else {
                    if (c.register(cuname, username, pass, gender, job, birthdate) == true) {
                        Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
