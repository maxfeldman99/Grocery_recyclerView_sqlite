package com.example.maxfeldman.grocery_recyclerview_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase myDataBase;
    private EditText myEditTextName;
    private TextView myTextViewAmount;
    private  int myAmount=0;
    private GroceryAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        myDataBase = dbHelper.getReadableDatabase();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new GroceryAdapter(this,getAllItems());
        recyclerView.setAdapter(myAdapter);

        myEditTextName = findViewById(R.id.et_name);
        myTextViewAmount = findViewById(R.id.tv_amount);

        Button buttonDec = findViewById(R.id.btn_decrease);
        Button buttonInc = findViewById(R.id.btn_increase);
        Button buttonAdd = findViewById(R.id.btn_add);

        buttonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease();
            }
        });
        buttonInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

    }

    private void increase(){
        myAmount++;
        myTextViewAmount.setText(String.valueOf(myAmount));
    }
    private void decrease(){
        if(myAmount>0){
        myAmount--;
        myTextViewAmount.setText(String.valueOf(myAmount));
        }
    }
    private void addItem(){
        if(myEditTextName.getText().toString().trim().length()==0 || myAmount==0){  // if our amount is zero we don't to add the item
            return;
        }

        String name = myEditTextName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME,name); // this way we add our name to the name column
        cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT,myAmount);

        myDataBase.insert(GroceryContract.GroceryEntry.TABLE_NAME,null,cv);
        myAdapter.swapCursor(getAllItems());

        myEditTextName.getText().clear(); // we empty the edit text for the next input


    }

    private Cursor getAllItems(){
        return myDataBase.query(
                GroceryContract.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC" //we order by descending order
        );
    }
}
