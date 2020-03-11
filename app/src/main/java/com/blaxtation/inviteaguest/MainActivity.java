package com.blaxtation.inviteaguest;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "FAILEDDDDDDD";
    FirebaseAuth auth;
    GoogleSignInClient googleSignInClient;

    public static final int MY_REQUEST_CODE_FOR_HOST=7117;
    public static final int MY_REQUEST_CODE_FOR_GUEST=7118;

    SignInButton HostSignIn;
    SignInButton GuestSignIn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();


        GoogleSignInOptions hostSignIn = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient= GoogleSignIn.getClient(this,hostSignIn);

        HostSignIn=findViewById(R.id.host_sign_in);
        HostSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent signinIntent=googleSignInClient.getSignInIntent();
                    startActivityForResult(signinIntent,MY_REQUEST_CODE_FOR_HOST);

            }
        });


        GuestSignIn=findViewById(R.id.guest_sign_in);
        GuestSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        Intent signinIntent = googleSignInClient.getSignInIntent();
                        startActivityForResult(signinIntent, MY_REQUEST_CODE_FOR_GUEST);


            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == MY_REQUEST_CODE_FOR_HOST) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                hostSignIn(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Internet Connection Required", Toast.LENGTH_SHORT).show();
                // ...
            }
        }
        else if (requestCode == MY_REQUEST_CODE_FOR_GUEST) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                guestSignIn(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Internet Connection Required", Toast.LENGTH_SHORT).show();

                // ...
            }
        }
    }



    private void hostSignIn(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(MainActivity.this,HomeScreenNavDrawer.class);
                            startActivity(i);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                        }

                        // ...
                    }
                });
    }

    private void guestSignIn(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(MainActivity.this,Bookings.class);
                            startActivity(i);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                        }

                        // ...
                    }
                });
    }


}





