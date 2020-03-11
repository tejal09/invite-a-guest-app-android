package com.blaxtation.inviteaguest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private List<guest> mguest;

    public RecyclerViewAdapter(Context mContext, List<guest> mguest) {
        this.mContext = mContext;
        this.mguest = mguest;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        final LayoutInflater inflater=LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.cardview_item_guest,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        guest guestimage= mguest.get(position);
        Glide.with(mContext)
                .load(guestimage.getImage())
                .centerCrop()
                .into(holder.guestimage);

        holder.guestname.setText(mguest.get(position).getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,GuestDescription.class);
                intent.putExtra("name",mguest.get(position).getName());
                intent.putExtra("image",mguest.get(position).getImage());
                intent.putExtra("guestDescription",mguest.get(position).getGuestDescription());
                intent.putExtra("sex",mguest.get(position).getSex());
                intent.putExtra("age",mguest.get(position).getAge());
                intent.putExtra("location",mguest.get(position).getLocation());
                intent.putExtra("language",mguest.get(position).getLanguage());
                intent.putExtra("rating",mguest.get(position).getRating());
                intent.putExtra("eventtype",mguest.get(position).getEventtype());
                intent.putExtra("category",mguest.get(position).getCategory());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mguest.size();
    }
}
