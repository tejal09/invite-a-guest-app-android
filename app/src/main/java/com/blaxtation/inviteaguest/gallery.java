package com.blaxtation.inviteaguest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class gallery extends AppCompatActivity {

   // List<galleryActivityGETSET> galleryActivityImages = new ArrayList<>();
    private FirebaseFirestore firestoreDB;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);



        firestoreDB = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv_galleryImages);
        LinearLayoutManager recyclerLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        buildGlide();

        getImageUrlsFromFirestoreDb();
    }

    private void buildGlide() {
        GlideBuilder gb = new GlideBuilder();

        //set mem cache size to 8% of available memory
        LruResourceCache lruMemCache = new LruResourceCache(getMemCacheSize(15));
        gb.setMemoryCache(lruMemCache);

        //set disk cache 300 mb
        InternalCacheDiskCacheFactory diskCacheFactory =
                new InternalCacheDiskCacheFactory(this, 300);
        gb.setDiskCache(diskCacheFactory);

        //set BitmapPool with 1/10th of memory cache's size
        LruBitmapPool bitmapPool = new LruBitmapPool(getMemCacheSize(1) / 10);
        gb.setBitmapPool(bitmapPool);

        //set custom Glide as global singleton
        Glide.init(this, gb);

    }

    private int getMemCacheSize(int percent) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager)
                getSystemService(ACTIVITY_SERVICE)).getMemoryInfo(mi);

        double availableMemory = mi.availMem;
        return (int) (percent * availableMemory / 100);
    }


    private void getImageUrlsFromFirestoreDb() {
        firestoreDB.collection("galleryActivityImages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> fileList = new ArrayList<String>();
                            //  Log.d(TAG, "number of images "+task.getResult().size());
                            for (DocumentSnapshot doc : task.getResult()) {
                                fileList.add(doc.getString("img"));
                            }
                            galleryAdapter imgRvAdapter = new galleryAdapter(fileList, gallery.this);
                            recyclerView.setAdapter(imgRvAdapter);

                        }
                    }
                });
    }




    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);



*/

        /*db= FirebaseFirestore.getInstance();

        db.collection("galleryActivityImages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getString("name")+ document.getString("GuestDescription"));
                                galleryActivityGETSET guest = new galleryActivityGETSET(document.getString("img"));

                                galleryActivityGETSET.add(galleryActivityImages);
                                adapter = new RecyclerViewAdapter(gallery.this, galleryActivityImages);
                                mRecyclerView.setAdapter(adapter);

                            }
                        }
                    }
                });*/
}