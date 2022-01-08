package com.example.golfscorecard;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PlayerRecyclerAdapter {

    class PlayerViewHolder extends RecyclerView.ViewHolder{
        EditText editText;

        public PlayerViewHolder(@NonNull View itemView){
            super(itemView);

            editText = itemView.findViewById(R.id.anzahlSchlaege);
        }

        //public void bind(final )
    }
}
