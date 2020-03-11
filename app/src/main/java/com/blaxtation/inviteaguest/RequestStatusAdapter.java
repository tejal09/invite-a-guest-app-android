package com.blaxtation.inviteaguest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestStatusAdapter extends RecyclerView.Adapter<RequestStatusAdapter.ViewHolder> {


    public List<requestdetailsgetset> requestdetailsgetsetList;
    private Context mContext;


    public RequestStatusAdapter(Context mContext,List<requestdetailsgetset> requestdetailsgetsetList){
        this.requestdetailsgetsetList=requestdetailsgetsetList;
        this.mContext=mContext;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_request,parent,false);
        return new ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.guestName.setText(requestdetailsgetsetList.get(position).getName());
        holder.status.setText(requestdetailsgetsetList.get(position).getStatus());



    }

    @Override
    public int getItemCount() {
        return requestdetailsgetsetList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        View mView;
        public TextView guestName;
        public TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            guestName=mView.findViewById(R.id.request_list_guestName);
            status=mView.findViewById(R.id.request_list_status);


        }
    }
}
