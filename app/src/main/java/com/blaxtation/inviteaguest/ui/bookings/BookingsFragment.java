package com.blaxtation.inviteaguest.ui.bookings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blaxtation.inviteaguest.Bookings;
import com.blaxtation.inviteaguest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingsFragment extends Fragment {



    public RecyclerView mMainList;
    public FirebaseFirestore mFirestore;
    public FirebaseAuth firebaseAuth;
    String guestnameString;


    public BookingListAdapter bookingListAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        View root = inflater.inflate(R.layout.fragment_bookings, container, false);





        mMainList=(RecyclerView)root.findViewById(R.id.booking_recyclerview);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(getActivity()));





        final List<bookingsGETSET>bookingsList=new ArrayList<>();



      //  bookingsList=new ArrayList<>();





        firebaseAuth= FirebaseAuth.getInstance();
        guestnameString = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName();

     //   hostnameString=firebaseAuth.getCurrentUser().getDisplayName();
        // firebaseAuth=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        mFirestore=FirebaseFirestore.getInstance();
        mFirestore.collection("InvitationRequest")
                .whereEqualTo("guestName",guestnameString )
                .get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                  private static final String TAG = "XXXXXXXXXXXXXXXX";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                               Log.d(TAG, document.getId() + " => " + document.getString("hostname"));
                               bookingsGETSET bookingsgetset= new bookingsGETSET
                                       (document.getString("hostname"),
                                               document.getString("invitedBy"),
                                               document.getString("emailAddress"),
                                               document.getString("contactNumber"),
                                               document.getString("audienceType"),
                                               document.getString("dateOfEvent"),
                                               document.getString("numbOfDays"),
                                               document.getString("hoursOfEngagement"),
                                               document.getString("venue"),
                                               document.getString("budget"),
                                               document.getString("eventDetails"),
                                               document.getString("guestExpectations"),
                                               document.getId());

                               bookingsList.add(bookingsgetset);
                                bookingListAdapter=new BookingListAdapter(bookingsList);
                                mMainList.setAdapter(bookingListAdapter);


                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return root;
    }




}