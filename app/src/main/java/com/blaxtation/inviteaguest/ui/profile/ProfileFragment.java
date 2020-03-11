package com.blaxtation.inviteaguest.ui.profile;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.blaxtation.inviteaguest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private  final int PICK_IMAGE_REQUEST = 71;
   private RadioGroup genderRadioGroup;
  private   String gender;
    private Uri imageUri;
   private Button profilePictureSelect,profilePictureUpload;
   private Button submit;

    FirebaseStorage storage;
    StorageReference storageReference;


    EditText nameEditText,ageEditText,languageEditText,
            cityEditText,stateEditText,
            countryEditText,emailaddressEditText,
            contactNumberEditText,categoryEditText,
            twitterEditText,instagramEditText,facebookEditText,
            wikipediaEditText,aboutyourselfEditText;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Objects.requireNonNull(getActivity()).requestWindowFeature(Window.FEATURE_NO_TITLE);
       // ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        firebaseFirestore=FirebaseFirestore.getInstance();



        nameEditText=root.findViewById(R.id.fragment_Profile_guest_name);
        ageEditText=root.findViewById(R.id.fragment_Profile_age);
        languageEditText=root.findViewById(R.id.fragment_Profile_language);
        cityEditText=root.findViewById(R.id.fragment_Profile_city);
        stateEditText=root.findViewById(R.id.fragment_Profile_state);
        countryEditText=root.findViewById(R.id.fragment_Profile_country);
        emailaddressEditText=root.findViewById(R.id.fragment_Profile_EmailAddress);
        contactNumberEditText=root.findViewById(R.id.fragment_profile_ContactNumber);
        categoryEditText=root.findViewById(R.id.fragment_profile_category);
        twitterEditText=root.findViewById(R.id.fragment_profile_twitterLink);
        instagramEditText=root.findViewById(R.id.fragment_profile_instagramLink);
        facebookEditText=root.findViewById(R.id.fragment_profile_facebookLink);
        wikipediaEditText=root.findViewById(R.id.fragment_profile_wikipedia);
        aboutyourselfEditText=root.findViewById(R.id.fragment_Profile_AboutYou);

        genderRadioGroup=root.findViewById(R.id.radio_gender);
        submit=root.findViewById(R.id.profile_submitButton);


        profilePictureSelect=root.findViewById(R.id.profile_picture_select);
        profilePictureUpload=root.findViewById(R.id.profile_picture_upload);
        profilePictureSelect.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                            PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {
                        chooseImage();
                    }
                } else{
                    chooseImage();
                    }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(imageUri!=null){
                    final ProgressDialog progressDialog=new ProgressDialog(getActivity());
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference ref=storageReference.child("images/*"+ UUID.randomUUID().toString());
                    ref.putFile(imageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();


                                    abc();

                                    Toast.makeText(getActivity(), "Successfully Submitted. We will contact you shortly.", Toast.LENGTH_LONG).show();

                                   // Toast.makeText(getActivity(), "Upload Successfull", Toast.LENGTH_LONG).show();

                                }

                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();


                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded"+(int)progress+"%");
                                }
                            });
                }
            }

            private void abc() {

                // genderRadioGroup.setOnCheckedChangeListener(ProfileFragment.this);
                final String fullname=nameEditText.getText().toString();
                final String age=ageEditText.getText().toString();
                final String language=languageEditText.getText().toString();
                final String city=cityEditText.getText().toString();
                final String state=stateEditText.getText().toString();
                final String country=countryEditText.getText().toString();
                final String email=emailaddressEditText.getText().toString();
                final String contactnumber=contactNumberEditText.getText().toString();
                final String category=categoryEditText.getText().toString();
                final String twitter=twitterEditText.getText().toString();
                final String instagram=instagramEditText.getText().toString();
                final String facebook=facebookEditText.getText().toString();
                final String wikipedia=wikipediaEditText.getText().toString();
                final String aboutYou=aboutyourselfEditText.getText().toString();
                //final String genderRadioBTN=gender;

                if (!TextUtils.isEmpty(fullname)
                        && !TextUtils.isEmpty(age)
                        && !TextUtils.isEmpty(language)
                        && !TextUtils.isEmpty(city)
                        && !TextUtils.isEmpty(state)
                        && !TextUtils.isEmpty(country)
                        && !TextUtils.isEmpty(contactnumber)
                        && !TextUtils.isEmpty(category)
                        && !TextUtils.isEmpty(twitter)
                        && !TextUtils.isEmpty(instagram)
                        && !TextUtils.isEmpty(facebook)
                        && !TextUtils.isEmpty(wikipedia)
                        && !TextUtils.isEmpty(aboutYou)
                        && !TextUtils.isEmpty(email))
                // && !TextUtils.isEmpty(genderRadioBTN))
                {

                    Map<String,String> guestProfileDetails = new HashMap<>();
                    guestProfileDetails.put("guestName",fullname);
                    guestProfileDetails.put("age",age);
                    //  guestProfileDetails.put("gender",gender);
                    guestProfileDetails.put("language",language);
                    guestProfileDetails.put("city",city);
                    guestProfileDetails.put("state",state);
                    guestProfileDetails.put("email",email);
                    guestProfileDetails.put("country",country);
                    guestProfileDetails.put("contactNumber",contactnumber);
                    guestProfileDetails.put("category",category);
                    guestProfileDetails.put("twitterLink",twitter);
                    guestProfileDetails.put("instagramLink",instagram);
                    guestProfileDetails.put("facebookLink",facebook);
                    guestProfileDetails.put("wikipediaLink",wikipedia);
                    guestProfileDetails.put("aboutYou",aboutYou);

                    firebaseFirestore.collection("NewGuestDB").document().set(guestProfileDetails)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){


                                        Toast.makeText(getActivity(), "Successfully Submitted. We will contact you shortly.", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(), "Something went wrong...", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });





        /*submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/


        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void chooseImage() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE_REQUEST);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData()!=null){

            imageUri=data.getData();
           /* try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContext(),imageUri);

            }
            catch (IOException e)
            {

                e.printStackTrace();
            }*/
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {


        switch (i){

            case R.id.radio_male:
                gender="Male";
                break;

            case R.id.radio_female:
                gender="Female";
                break;

            case R.id.radio_transgender:
                gender="Transgender";
                break;


        }
    }
}