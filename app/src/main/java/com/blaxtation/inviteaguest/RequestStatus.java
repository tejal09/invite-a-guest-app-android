package com.blaxtation.inviteaguest;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestStatus extends AppCompatActivity {

    private RecyclerView mMainList;
    private FirebaseFirestore  mFirestore;
    private FirebaseAuth firebaseAuth;
    String userID,mobileNumber;
    private List<requestdetailsgetset> requestList;
    private RequestStatusAdapter requestStatusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status);


        requestList=new ArrayList<>();

        mMainList=findViewById(R.id.request_list_recycler_view);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(requestStatusAdapter);

        firebaseAuth= FirebaseAuth.getInstance();
       userID= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        mobileNumber=firebaseAuth.getCurrentUser().getPhoneNumber();
        mFirestore=FirebaseFirestore.getInstance();


        //requestStatusAdapter=new RequestStatusAdapter(requestList);

        mFirestore.collection("InvitationRequest")
                .whereEqualTo("userid",userID)
                .get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    private static final String TAG = "tag";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                Log.d(TAG, document.getId() + " => " + document.getString("name") + document.getString("GuestDescription"));
                                requestdetailsgetset requestdetailsgetset= new requestdetailsgetset(
                                        document.getString("guestName"),
                                        document.getString("status"));
                                requestList.add(requestdetailsgetset);


                                requestStatusAdapter = new RequestStatusAdapter(RequestStatus.this, requestList);
                                mMainList.setAdapter(requestStatusAdapter);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());

                        }
                    }
                });

    }
}
