package com.blaxtation.inviteaguest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllTalents extends AppCompatActivity {

    List<guest> guestList = new ArrayList<>();
   /* RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;*/

    FirebaseFirestore db;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_talents);


        db = FirebaseFirestore.getInstance();
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_ALl_Talents);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

       // final CollectionReference guestlist = db.collection("guest");

        db.collection("guest")
                //.whereEqualTo("name", true)
                .get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    private static final String TAG = "HomeScreenNavDrawer";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getString("name") + document.getString("GuestDescription"));
                                guest guest = new guest(document.getString("name"),
                                        document.getString("image"),
                                        document.getString("sex"),
                                        document.getString("age"),
                                        document.getString("location"),
                                        document.getString("rating"),
                                        document.getString("GuestDescription"),
                                        document.getString("language"),
                                        document.getString("eventtype"),
                                        document.getString("category"));
                                guestList.add(guest);
                                adapter = new RecyclerViewAdapter(AllTalents.this, guestList);
                                mRecyclerView.setAdapter(adapter);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
