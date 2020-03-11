package com.blaxtation.inviteaguest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView guestname;
    ImageView guestimage;
   TextView guestDescription;
    CardView cardView;


    public MyViewHolder(@NonNull View itemView){
        super (itemView);



        guestname=(TextView)itemView.findViewById(R.id.guest_name);
        guestimage=(ImageView)itemView.findViewById(R.id.guest_image);

        cardView=(CardView)itemView.findViewById(R.id.cardview);
       guestDescription=(TextView)itemView.findViewById(R.id.guest_desc_description);





    }



    }