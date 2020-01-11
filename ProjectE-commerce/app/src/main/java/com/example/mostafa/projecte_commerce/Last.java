package com.example.mostafa.projecte_commerce;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class Last extends AppCompatActivity implements LocationListener {
Customers C;
    private LocationManager locationManager;
    private EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        Button submit=(Button)findViewById(R.id.btn_submit);
        C=new Customers(getApplicationContext());
        e=(EditText) findViewById(R.id.btn_address);
        Date a=new Date();
        C=new Customers(getApplicationContext());
        String b=String.valueOf(a.getDate());
        String c=String.valueOf((a.getMonth()+1));
        String d=String.valueOf(a.getYear()+1900);
        final String result=b+"/"+c+"/"+d;
        final String userid= (String) getIntent().getExtras().get("get_id3");













        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!(e.getText().toString().length()==0))
                {
                    if(C.insertorders(e.getText().toString(),result,userid)==true)
                            Toast.makeText(getApplicationContext(),"Saved ",Toast.LENGTH_LONG).show();
                       else
                        Toast.makeText(getApplicationContext(),"error ",Toast.LENGTH_LONG).show();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(),"Fadya",Toast.LENGTH_LONG).show();
                    }

            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude=location.getLongitude();
        double latitude=location.getLatitude();
        e.setText("longitude:"+longitude+"\n"+"latitude: "+latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
