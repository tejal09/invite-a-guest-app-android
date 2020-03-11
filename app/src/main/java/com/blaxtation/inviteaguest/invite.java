package com.blaxtation.inviteaguest;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class invite extends AppCompatActivity{


    Button InviteButton;
    EditText hostName,invitedBy,audienceType,dateOfEvent,noOfDays,hoursOfEng,
            venueOfPlace,budgetOfHost,eventDetails,guestExpectations,hostEmailAddress,hostContactNumber;


    String userId;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);


        final Intent intent = getIntent();
        final String guestName = getIntent().getStringExtra("name");


        InviteButton=(Button)findViewById(R.id.inviteButton);
        hostName=findViewById(R.id.host_name);
        invitedBy=findViewById(R.id.invitedBy);
        hostContactNumber=findViewById(R.id.hostContactNumber);
        hostEmailAddress=findViewById(R.id.hostEmailAddress);

        audienceType=findViewById(R.id.audienceType);
        dateOfEvent=findViewById(R.id.eventDate);
        noOfDays=findViewById(R.id.noOfDays);
        hoursOfEng=findViewById(R.id.hoursOfEngagement);
        venueOfPlace=findViewById(R.id.venue);
        budgetOfHost=findViewById(R.id.budget);
        eventDetails=findViewById(R.id.eventDetails);
        guestExpectations=findViewById(R.id.guestExpectations);

        firebaseAuth= FirebaseAuth.getInstance();
        userId= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

        firebaseFirestore=FirebaseFirestore.getInstance();




        InviteButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {

                final GMailSender sender = new GMailSender("cjatin560@gmail.com", "Jatin123@");

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {

                try {
                    sender.sendMail("New Invitation - INVITE A GUEST",
                            "Hello Sir/Madam," +
                                    "\n\n Someone just invited you to their event, To check please open our app to get further info.",
                            "cjatin560@gmail.com",
                            "ooops4@gmail.com");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
                        return null;
                    }
                }.execute();



                final String name = guestName;
                final String hostname = hostName.getText().toString();
                final String invitedby = invitedBy.getText().toString();
                final String audiencetype = audienceType.getText().toString();
                final String dateofevent = dateOfEvent.getText().toString();
                final String noofdays = noOfDays.getText().toString();
                final String hoursofeng = hoursOfEng.getText().toString();
                final String venue = venueOfPlace.getText().toString();
                final String budget = budgetOfHost.getText().toString();
                final String eventdetails = eventDetails.getText().toString();
                final String guestexpectations = guestExpectations.getText().toString();
                final String hostemailaddress = hostEmailAddress.getText().toString();
                final String hostcontactnumber = hostContactNumber.getText().toString();


                final String userid=userId;



                if (!TextUtils.isEmpty(hostname)
                        && !TextUtils.isEmpty(invitedby)
                        && !TextUtils.isEmpty(audiencetype)
                        && !TextUtils.isEmpty(dateofevent)
                        && !TextUtils.isEmpty(noofdays)
                        && !TextUtils.isEmpty(hoursofeng)
                        && !TextUtils.isEmpty(venue)
                        && !TextUtils.isEmpty(budget)
                        && !TextUtils.isEmpty(eventdetails)
                        && !TextUtils.isEmpty(hostcontactnumber)
                        && !TextUtils.isEmpty(hostemailaddress)
                        && !TextUtils.isEmpty(guestexpectations)) {


                  /*  if (TextUtils.isEmpty(hostName.getText())) {
                        hostName.setError("Host Name is Required"); */

                        Map<String, String> inviteRequest = new HashMap<>();
                        inviteRequest.put("guestName", name);
                        inviteRequest.put("hostname", hostname);
                        inviteRequest.put("invitedBy", invitedby);
                        inviteRequest.put("emailAddress", hostemailaddress);
                        inviteRequest.put("contactNumber", hostcontactnumber);
                        inviteRequest.put("audienceType", audiencetype);
                        inviteRequest.put("dateOfEvent", dateofevent);
                        inviteRequest.put("numbOfDays", noofdays);
                        inviteRequest.put("hoursOfEngagement", hoursofeng);
                        inviteRequest.put("venue", venue);
                        inviteRequest.put("budget", budget);
                        inviteRequest.put("eventDetails", eventdetails);
                        inviteRequest.put("guestExpectations", guestexpectations);
                        inviteRequest.put("status", "Currently in review");
                        inviteRequest.put("userid",userid);


                        firebaseFirestore.collection("InvitationRequest").document().set(inviteRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(invite.this, "Successfully Submitted. We will contact you shortly.", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(invite.this, HomeScreenNavDrawer.class));

                                } else {
                                    Toast.makeText(invite.this, "Firestore Error...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                    } else {
                        Toast.makeText(invite.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    }
                }

        });



        final Calendar myCalendar = Calendar.getInstance();

        @SuppressLint("CutPasteId") final EditText edittext = (EditText) findViewById(R.id.eventDate);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                new DatePickerDialog(invite.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
}
