package com.blaxtation.inviteaguest;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingDetails extends Fragment {
    public BookingDetails() {
        // Required empty public constructor
    }

    private TextView hostnameEditText,invitedByTextView,emailAddressTextView,contactNumberTextView,audienceTypeTextView,
            dateOfEventTextView,NnoOfDaysTextView,
            hoursOfEngagementTextView,venueTextView,budgetTextView,eventDetailsTextView,guestExpectationsTextView;
    private Button acceptButton,rejectButton;
    private FirebaseFirestore mFirestore;
    private FirebaseAuth firebaseAuth;
    private String DOCID;


    private String accept="Accepted";
    private String reject="Rejected";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_booking_details, container, false);


        firebaseAuth= FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();

        hostnameEditText=root.findViewById(R.id.fragment_host_name);
        invitedByTextView=root.findViewById(R.id.fragment_invitedBy);
        emailAddressTextView=root.findViewById(R.id.fragment_hostEmailAddress);
        contactNumberTextView=root.findViewById(R.id.fragment_hostContactNumber);
        audienceTypeTextView=root.findViewById(R.id.fragment_audienceType);
        dateOfEventTextView=root.findViewById(R.id.fragment_eventDate);
        NnoOfDaysTextView=root.findViewById(R.id.fragment_noOfDays);
        hoursOfEngagementTextView=root.findViewById(R.id.fragment_hoursOfEngagement);
        venueTextView=root.findViewById(R.id.fragment_venue);
        budgetTextView=root.findViewById(R.id.fragment_budget);
        eventDetailsTextView=root.findViewById(R.id.fragment_eventDetails);
        guestExpectationsTextView=root.findViewById(R.id.fragment_guestExpectations);

        acceptButton=root.findViewById(R.id.accept_Button);
        rejectButton=root.findViewById(R.id.reject_Button);

        Bundle bundle=getArguments();
        String hostname=bundle.getString("hostname");
        String invitedBy=bundle.getString("invitedBy");
        String NnoOfDays=bundle.getString("NnoOfDays");
        String audienceType=bundle.getString("audienceType");
        String budget=bundle.getString("budget")+" Thousand";
        String dateOfEvent=bundle.getString("dateOfEvent");
        String eventDetails=bundle.getString("eventDetails");
        String guestExpectations=bundle.getString("guestExpectations");
        String hoursOfEngagement=bundle.getString("hoursOfEngagement");
        String venue=bundle.getString("venue");
        String emailAddress=bundle.getString("emailAddress");
        String contactNumber=bundle.getString("contactNumber");
        final String documentID=bundle.getString("docid");

       // contactNumberTextView.setText(String.valueOf(bundle.getString("contactNumber")));
        //^^^^^showFrag1.setText(String.valueOf(bundle.getString("hello"))); extra method for above as same^^^
        final Map<String, String> statusAccepted = new HashMap<>();

        final Map<String, String> statusRejected = new HashMap<>();


        hostnameEditText.setText(hostname);
        invitedByTextView.setText(invitedBy);
        emailAddressTextView.setText(emailAddress);
        contactNumberTextView.setText(contactNumber);
        audienceTypeTextView.setText(audienceType);
        dateOfEventTextView.setText(dateOfEvent);
        NnoOfDaysTextView.setText(NnoOfDays);
        hoursOfEngagementTextView.setText(hoursOfEngagement);
        venueTextView.setText(venue);
        budgetTextView.setText(budget);
        eventDetailsTextView.setText(eventDetails);
        guestExpectationsTextView.setText(guestExpectations);

      statusAccepted.put("status",accept);
      statusRejected.put("status",reject);

        final FieldPath field = FieldPath.of("status");


        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirestore.collection("InvitationRequest").document(documentID).update(field,"Accepted").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getActivity(), "Invitation Accepted succesfully. Details will be shared to you shortly", Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(invite.this, HomeScreenNavDrawer.class));

                        } else {
                            Toast.makeText(getActivity(), "Something went wrong. Please Try Again.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });



            }
        });


        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirestore.collection("InvitationRequest").document(documentID).update(field,"Rejected").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getActivity(), "Invitation Rejected", Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(invite.this, HomeScreenNavDrawer.class));

                        } else {
                            Toast.makeText(getActivity(), "Something went wrong. Please Try Again.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });





        return  root;

    }

}
