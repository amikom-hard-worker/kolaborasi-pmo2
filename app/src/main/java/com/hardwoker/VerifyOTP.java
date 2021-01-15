package com.hardwoker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOTP extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private EditText mOtpText;
    private Button mVerifybtn;

    private ProgressBar verifyProgressBar;
    private String mAuthVerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        mOtpText = findViewById(R.id.verificattion_code_et);
        mVerifybtn = findViewById(R.id.Button_otp);
        verifyProgressBar = findViewById(R.id.otp_progress_bar);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mAuthVerificationId = getIntent().getStringExtra("AuthCredentials");

        mVerifybtn.setOnClickListener(view -> {
            String otp = mOtpText.getText().toString();
            if (otp.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fill ", Toast.LENGTH_LONG).show();
            }else
            {
                verifyProgressBar.setVisibility(View.VISIBLE);
                Log.d("Print","7");

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthVerificationId,otp);
                signInWithOhoneAuthCredential(credential);
            }
        });


    }
    private void signInWithOhoneAuthCredential(PhoneAuthCredential credential){
        Log.d("Print","8");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyOTP.this, task -> {
                    if (task.isSuccessful()){
                        registerPage();
                    }else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(getApplicationContext(),"Error in Verifying OTP", Toast.LENGTH_LONG).show();
                        }
                    }
                    verifyProgressBar.setVisibility(View.INVISIBLE);
                });
    }

    private void registerPage(){
        Log.d("Print","9");
        Intent HomeiNtent = new Intent(VerifyOTP.this,MainActivity.class);
        HomeiNtent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        HomeiNtent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(HomeiNtent);
        finish();
    }
}
