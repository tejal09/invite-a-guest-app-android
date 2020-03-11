package com.blaxtation.inviteaguest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class GuestDescription extends AppCompatActivity {

    private Context mContext;
    private List<guest> mguest;

    public TextView guestnameTextView,guestdescriptionTextView;
    public ImageView guestImage;
    public TextView sexTextView,ageTextView,locationTextView,languageTextView,eventtypeTextView,categoryTextView;
    public RatingBar ratingBar;
    Float ratingf;
    Button EnquireButton;


    @Override
    protected void onCreate(Bundle savedInstBundle) {
        super.onCreate(savedInstBundle);
        setContentView(R.layout.activity_guest_description);


        guestnameTextView=(TextView)findViewById(R.id.guest_name_desc);
        guestdescriptionTextView=(TextView)findViewById(R.id.guest_desc_description);
        guestImage=(ImageView)findViewById(R.id.guest_desc_image);
        sexTextView=findViewById(R.id.guest_desc_sex);
        ageTextView=findViewById(R.id.guest_desc_age);
        locationTextView=findViewById(R.id.guest_desc_location);
        languageTextView=findViewById(R.id.guest_desc_language);
        eventtypeTextView=findViewById(R.id.guest_desc_eventtype);
        categoryTextView=findViewById(R.id.guest_desc_category);
        ratingBar=(RatingBar)findViewById(R.id.guest_desc_rating);



        Intent intent = getIntent();
       final String Name= intent.getExtras().getString("name");
        String Image= intent.getExtras().getString("image");
        String GuestDescription=intent.getExtras().getString("guestDescription");
        String sex=intent.getExtras().getString("sex");
        String age=intent.getExtras().getString("age");
        String location=intent.getExtras().getString("location");
        String language=intent.getExtras().getString("language");
        String eventtype=intent.getExtras().getString("eventtype");
        String category=intent.getExtras().getString("category");
        String rating=intent.getExtras().getString("rating");
        ratingf=Float.parseFloat(rating);

        guestnameTextView.setText(Name);
        Glide.with(this)
                .load(Image)
                .centerCrop()
                .into(guestImage);
        guestdescriptionTextView.setText(GuestDescription);
        sexTextView.setText(sex);
        ageTextView.setText(age);
        locationTextView.setText(location);
        languageTextView.setText(language);
        eventtypeTextView.setText(eventtype);
        categoryTextView.setText(eventtype);
        categoryTextView.setText(category);
        ratingBar.setRating(ratingf);



        EnquireButton=findViewById(R.id.enquire);

        EnquireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent=new Intent(GuestDescription.this, invite.class);
                intent.putExtra("name",Name);

                 startActivity(intent);


            }
        });





    }
}


