package com.example.ravi.insertsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText Username ,Password,search;
    SQLitedatabaseAdapter sqLitedatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username=(EditText)findViewById(R.id.Username);
        Password=(EditText)findViewById(R.id.Password);
        search=(EditText)findViewById(R.id.search);
        sqLitedatabaseAdapter=new SQLitedatabaseAdapter(this);



    }

    public void adduser(View view)
    {
        String username=Username.getText().toString();
        String password=Password.getText().toString();

       long id=sqLitedatabaseAdapter.insertData(username,password);
        if(id<0)
        {
            Message.message(this,"Unsuccessful sorry try again");
        }
        else{
            Message.message(this,"Successful hurray!!!");
        }
    }

    public void viewdetails(View view){

        String data=sqLitedatabaseAdapter.getAllData();
        Message.message(this,data);

    }

    public void getdata(View view)
    {
        String s1=search.getText().toString();
        String s2=sqLitedatabaseAdapter.getData(s1);
        Message.message(this,s2);
    }
}
