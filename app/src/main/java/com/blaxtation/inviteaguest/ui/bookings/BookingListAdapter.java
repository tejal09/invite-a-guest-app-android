package com.blaxtation.inviteaguest.ui.bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.blaxtation.inviteaguest.BookingDetails;
import com.blaxtation.inviteaguest.R;

import java.util.List;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.ViewHolder> {

    public List<bookingsGETSET>bookingsGETSETList;
    public BookingListAdapter(List<bookingsGETSET>bookingsGETSETList){

        this.bookingsGETSETList=bookingsGETSETList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bookings_textview,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.hostname.setText(bookingsGETSETList.get(position).getHostnamee());



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BookingDetails bookingDetails = new BookingDetails();
                AppCompatActivity activity=(AppCompatActivity) view.getContext();

                Bundle bundle=new Bundle();
                bookingDetails.setArguments(bundle);

                bundle.putString("hostname",bookingsGETSETList.get(position).getHostnamee());
                bundle.putString("invitedBy",bookingsGETSETList.get(position).getInvitedby());
                bundle.putString("NnoOfDays",bookingsGETSETList.get(position).getNumbofdays());
                bundle.putString("audienceType",bookingsGETSETList.get(position).getAudiencetype());
                bundle.putString("budget",bookingsGETSETList.get(position).getBudgettotal());
                bundle.putString("dateOfEvent",bookingsGETSETList.get(position).getDateofevent());
                bundle.putString("eventDetails",bookingsGETSETList.get(position).getEventdetails());
                bundle.putString("guestExpectations",bookingsGETSETList.get(position).getGuestexpectations());
                bundle.putString("hoursOfEngagement",bookingsGETSETList.get(position).getHoursofengagement());
                bundle.putString("venue",bookingsGETSETList.get(position).getVenuelocation());
                bundle.putString("emailAddress",bookingsGETSETList.get(position).getEmailaddress());
                bundle.putString("contactNumber",bookingsGETSETList.get(position).getContactnumber());
                bundle.putString("docid",bookingsGETSETList.get(position).getDocid());

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_bookings, bookingDetails).addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookingsGETSETList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public TextView hostname;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            hostname=mView.findViewById(R.id.bookings_list_hostName);

        }
    }
}
