package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHandler myDb;
    EditText name,password;
    Button add,delete,update,select,signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DBHandler(this);

        name = (EditText)findViewById(R.id.t1);
        password = (EditText)findViewById(R.id.t2);
        add = (Button) findViewById(R.id.b2);
        delete = (Button) findViewById(R.id.b4);
        update = (Button) findViewById(R.id.b5);
        select = (Button) findViewById(R.id.b1);
        signIn = (Button) findViewById(R.id.b3);
        addInfo();
        readAllInfo();
        deleteInfo();
        UpdateInfo();

    }
    public void addInfo(){
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.addInfo(name.getText().toString(),
                                password.getText().toString());


                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public Cursor readAllInfo(){
        select.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.readAllInfo();
                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error ","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Password :" + res.getString(2) + "\n\n");

                        }

                        //show all data
                        showMessage("Data ",buffer.toString());
                    }
                }
        );
    }
    public void deleteInfo() {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteInfo(Id.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateInfo(){
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateInfo(Id.getText().toString(),
                                name.getText().toString(),
                                password.getText().toString());

                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
