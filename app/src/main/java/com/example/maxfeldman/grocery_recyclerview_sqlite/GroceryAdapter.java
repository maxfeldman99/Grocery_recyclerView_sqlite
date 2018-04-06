package com.example.maxfeldman.grocery_recyclerview_sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MAX FELDMAN on 07/04/2018.
 */

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    private Context myContext;
    private Cursor myCursor;


    public GroceryAdapter(Context context, Cursor cursor) {
        myContext = context;
        myCursor = cursor;


    } // cursor it's what takes the data from out database



    public class GroceryViewHolder extends RecyclerView.ViewHolder {

        public TextView nameText;
        public TextView countText;

        @SuppressLint("CutPasteId")
        public GroceryViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.tv_name_item);
            countText = itemView.findViewById(R.id.tv_amount_item);

        }
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View view = inflater.inflate(R.layout.grocery_item,parent,false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, int position) {
        if(!myCursor.moveToPosition(position)){
            return; // we make sure that the cursor existing
        }
        String name = myCursor.getString(myCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        int amount = myCursor.getInt(myCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT)); // to read from data base

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return myCursor.getCount(); // we pass a cursor when we create our data base
    }

    public void swapCursor(Cursor newCursor){ //every time we want to update we need to pass a new cursor
        if(myCursor!=null){ // if the one we currently have is not null
            myCursor.close(); // we get rid of it
        }

        myCursor = newCursor; // we set it to the new cursor

        if(newCursor !=null){
            notifyDataSetChanged();
        }
    }
}

