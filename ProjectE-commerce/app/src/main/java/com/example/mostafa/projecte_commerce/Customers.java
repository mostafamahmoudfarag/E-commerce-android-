package com.example.mostafa.projecte_commerce;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by mostafa on 12/1/2018.
 */

public class Customers extends SQLiteOpenHelper {
    public static final String databasename = "Ecommer.db";
    SQLiteDatabase data;
public String U,P;

    public Customers(Context context) {
        super(context, databasename, null, 15);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table customer (id integer primary key autoincrement,name text,username text " +
                ",password text,gender text ,job text,birthdate text)");
        db.execSQL("create table checker(user text,pass text)");
        db.execSQL("create table product (pid integer primary key autoincrement  ,proname text " +
                ",price integer,quantity integer)");
        db.execSQL("create table saveorder(pdn text,pds text,pdq text)");
        db.execSQL("create table orders(ordid integer primary key autoincrement," +
                "orddate text,custid integer,address text," +
                "FOREIGN KEY(custid) REFERENCES customer(id))");
        ///////////////////////////////////////
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Samsung galaxy A8',8600,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Samsung galaxy S9 plus',8500,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Samsung J7 pro',6000,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Nokia 7 plus',5000,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Samsung galaxy A5',7000,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Huawei mate 10',4000,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Huawei GR5',3000,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('Huawei Y6 prime',3500,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('iphone 7 plus',12000,100)");
        db.execSQL("insert into product(proname,price,quantity)" +
                "values('iphone 8 plus',15000,100)");
/*
db.execSQL("create table orderdetails(ordernumber integer,productname text ,productquantity text,foreign key(ordernumber) " +
        "references ");
*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists checker");
        db.execSQL("drop table if exists product");
        db.execSQL("drop table if exists saveorder");
        db.execSQL("drop table if exists orders");
        onCreate(db);
    }
    public String totalmoney()
    {
          data=getReadableDatabase();
        Cursor c=data.rawQuery("select * from saveorder",null);
        c.moveToFirst();
        int a=0;
        while(!c.isAfterLast())
        {
             int s=Integer.parseInt(c.getString(1));
            int q=Integer.parseInt(c.getString(2));
            a+=s*q;
            c.moveToNext();
        }
      return String.valueOf(a);
    }
public  boolean savingorder(String pdn,String pds,String pdq)
{
    data=getWritableDatabase();
    ContentValues row=new ContentValues();
    row.put("pdn",pdn);
    row.put("pds",pds);
    row.put("pdq",pdq);
    long res = data.insert("saveorder", null, row);
    if (res == -1)
        return false;
    else
        return true;


}
public boolean insertorders(String address,String date,String custid)
{
    data=getWritableDatabase();
    ContentValues row=new ContentValues();
    row.put("orddate",date);
    row.put("custid",custid);
    row.put("address",address);
    long res = data.insert("orders", null, row);
    if (res == -1)
        return false;
    else
        return true;



}
public ArrayList<String> returnsavingorder()
{
    data =getReadableDatabase();
    Cursor C=data.rawQuery("select * from saveorder",null);
    ArrayList<String>arrayList=new ArrayList<>();
    C.moveToFirst();
    while(!C.isAfterLast())
    {
        String a=C.getString(0);
        String b=C.getString(1);
        String c=C.getString(2);
        arrayList.add(a+"    "+b+"  "+c);
        C.moveToNext();
    }

return arrayList;
}
public void clearallfromdatabase()
{
data =getReadableDatabase();
    data.execSQL("delete from saveorder" );

}

public Integer deleteproduct(String pname)
{
    data=getReadableDatabase();
    return data.delete("saveorder","pdn like?",new String[]{pname});
}
public boolean updateorder(String nameofproduct,String quantity)
{
    String S="";
    data=getReadableDatabase();
    Cursor c=data.rawQuery("select * from product where proname like?",new String[]{nameofproduct});
    c.moveToFirst();
    if(c.isAfterLast()==true)
        return false;
    else
        S=c.getString(2);


    data=getWritableDatabase();
    ContentValues row=new ContentValues();
    row.put("pdn",nameofproduct);
    row.put("pds",S);
    row.put("pdq",quantity);
data.update("saveorder",row,"pdn like?",new String[]{nameofproduct});
return true;
}



    public boolean register(String name, String username, String pass, String gender, String job,String date) {
        data = getReadableDatabase();
        ContentValues row = new ContentValues();
        Cursor C = data.rawQuery("select * from customer where username like?", new String[]{username});
        if (C.isAfterLast() == true) {
            data = getWritableDatabase();
            row.put("name", name);
            row.put("username", username);
            row.put("password", pass);
            row.put("gender", gender);
            row.put("job", job);
            row.put("birthdate",date);
            long res = data.insert("customer", null, row);
            if (res == -1)
                return false;
            else
                return true;
        } else
            return false;
    }


    public boolean log(String n, String e) {
        data = getReadableDatabase();
        ContentValues row = new ContentValues();

        Cursor raw = data.rawQuery("select * from customer where username like?", new String[]{n});
        if (raw.isAfterLast())
            return false;
        raw.moveToFirst();
        if ("".equals(raw.getString(3)))
            return false;
        if (e.equals(raw.getString(3))) {
            return true;
        } else
            return false;
    }
    public boolean rememberme(String user,String pass)
    {

        data = getReadableDatabase();
        Cursor R=data.rawQuery("select * from checker ",null);

        ContentValues row = new ContentValues();
        Cursor C = data.rawQuery("select * from checker where user like?", new String[]{user});
        if (C.isAfterLast() == true) {
            data = getWritableDatabase();
            row.put("user", user);
            row.put("pass", pass);
        long res=data.insert("checker",null,row);
            if(res==-1)
                return false;
            else
                return true;
        }
        else
            return false;

    }
        public Integer deleteemlement(String a)
        {
            data=getWritableDatabase();
            return data.delete("checker","user=?",new String[]{a});
        }
        public void retrivedata()
        {
            data=getReadableDatabase();
            Cursor c=data.rawQuery("select * from checker",null);
            c.moveToFirst();
          if(c.isAfterLast()==true)
          {
              U="";
              P="";
          }
          else
          {
              U=c.getString(0);
              P=c.getString(1);
          }
        }
        public String get_id(String name)
        {
            data=getReadableDatabase();
            Cursor c=data.rawQuery("select * from customer where username like?",new String[]{name});
            c.moveToFirst();
            return c.getString(0);

        }
}

