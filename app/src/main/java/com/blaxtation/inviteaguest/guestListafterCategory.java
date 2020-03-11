package com.blaxtation.inviteaguest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class guestListafterCategory extends AppCompatActivity {

    List<guest> guestList = new ArrayList<>();

    FirebaseFirestore db;
    RecyclerViewAdapter adapter;
    Toolbar toolbar;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_listafter_category);

        toolbar = findViewById(R.id.categorynametoolbar);

        String categoryname = getIntent().getExtras().getString("category");
        categoryname.toString();

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            toolbar.setTitle(bundle.getString("category"));
            if (categoryname.equalsIgnoreCase("Actors")) {

                db = FirebaseFirestore.getInstance();
                final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                db.collection("guest")
                        .whereArrayContainsAny("guestcategory", Collections.singletonList("actor"))
                        .get()

                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
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
                                        adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                        mRecyclerView.setAdapter(adapter);
                                    }
                                } else {
                                }
                            }
                        });
            }
            else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Anchors")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("anchor"))
                            .get()

                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                private static final String TAG = "HomeScreenNavDrawer";

                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Comedians")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("comedian"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Dancers")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("dancer"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("DJ")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("dj"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Fashion")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("fashion"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Judges")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("judge"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Singers")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("singer"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Speakers")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("speaker"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else if (bundle != null) {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Writers")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                    //final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("writer"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            } else  {
                toolbar.setTitle(bundle.getString("category"));
                if (categoryname.equalsIgnoreCase("Sportspersons")) {

                    db = FirebaseFirestore.getInstance();
                    final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_guestListafterCategory);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

                   // final CollectionReference guestlist = db.collection("guest");

                    db.collection("guest")
                            .whereArrayContainsAny("guestcategory", Collections.singletonList("sportsperson"))
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
                                            adapter = new RecyclerViewAdapter(guestListafterCategory.this, guestList);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    } else {
                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

            }
        }
    }
}}}}}}}}}}}
