package com.blaxtation.inviteaguest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class contact_us extends AppCompatActivity {


    Button contactusbutton;
    EditText fullnameEditText,emailEditText,contactnumberEditText,messageEditText;



    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        contactusbutton=findViewById(R.id.contactus_Submit);
        fullnameEditText=findViewById(R.id.contactus_name);
        emailEditText=findViewById(R.id.contactus_email);
        contactnumberEditText=findViewById(R.id.contactus_phonenumb);
        messageEditText=findViewById(R.id.contactus_Message);

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseFirestore=FirebaseFirestore.getInstance();

        contactusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fullname = fullnameEditText.getText().toString();
                final String email = emailEditText.getText().toString();
                final String contactnumber = contactnumberEditText.getText().toString();
                final String message = messageEditText.getText().toString();


                if (!TextUtils.isEmpty(fullname)
                        && !TextUtils.isEmpty(email)
                        && !TextUtils.isEmpty(contactnumber)
                        && !TextUtils.isEmpty(message)){

                    Map<String,String> getintouchwithus = new HashMap<>();
                    getintouchwithus.put("hostName",fullname);
                    getintouchwithus.put("hostEmail",email);
                    getintouchwithus.put("contactNumber",contactnumber);
                    getintouchwithus.put("message",message);

                    firebaseFirestore.collection("GetInTouchWithUsDB").document().set(getintouchwithus).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                            Toast.makeText(contact_us.this, "Successfully Submitted. We will contact you shortly.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(contact_us.this, "Firestore Error...", Toast.LENGTH_SHORT).show();

                        }
                        }
                    });



                }



            }
        });






    }
}
