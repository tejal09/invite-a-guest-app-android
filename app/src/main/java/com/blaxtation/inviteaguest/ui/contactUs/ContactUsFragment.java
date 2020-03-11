package com.blaxtation.inviteaguest.ui.contactUs;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blaxtation.inviteaguest.R;
import com.blaxtation.inviteaguest.contact_us;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ContactUsFragment extends Fragment {

    Button contactusbutton;
    EditText fullnameEditText,emailEditText,contactnumberEditText,messageEditText;



    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Objects.requireNonNull(getActivity()).requestWindowFeature(Window.FEATURE_NO_TITLE);
       // ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        View root = inflater.inflate(R.layout.fragment_contactus, container, false);


        contactusbutton=root.findViewById(R.id.fragment_contactus_SubmitButton);
        fullnameEditText=root.findViewById(R.id.fragment_contactus_name);
        emailEditText=root.findViewById(R.id.fragment_contactus_email);
        contactnumberEditText=root.findViewById(R.id.fragment_contactus_phonenumb);
        messageEditText=root.findViewById(R.id.fragment_contactus_Message);

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

                    Map<String,String> getintouchwithusGUESTFRAGMENT = new HashMap<>();
                    getintouchwithusGUESTFRAGMENT.put("hostName",fullname);
                    getintouchwithusGUESTFRAGMENT.put("hostEmail",email);
                    getintouchwithusGUESTFRAGMENT.put("contactNumber",contactnumber);
                    getintouchwithusGUESTFRAGMENT.put("message",message);


                    firebaseFirestore.collection("GetInTouchWithUsGUESTFRAGMENT").document().set(getintouchwithusGUESTFRAGMENT).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(getActivity(), "Successfully Submitted. We will contact you shortly.", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getActivity(), "Something went wrong....", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });



                }



            }
        });

        return root;
    }
}