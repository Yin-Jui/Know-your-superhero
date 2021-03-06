package com.example.knowyoursuperhero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirstFragment extends Fragment implements FirebaseAuth.AuthStateListener {

    TextView user_info;
    Button verify;
    int i = 1;
    String TAG = "FirstFragment";

    @Override
    public View onCreateView(

            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        user_info = view.findViewById(R.id.user_info);
        verify = view.findViewById(R.id.verify);

        verify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()) {
                           Snackbar.make(view, "Verify email sent", Snackbar.LENGTH_LONG).show();
                       }
                   }
               });

            }
        });

        view.findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.herodatabse_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HeroBaseActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.quiz_button).setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.hero_fight).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HeroFightActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.gps_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        user_info.setText("Email");
        Log.d(TAG, "AUTH CHANGED");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Utility.Companion.checkExist(uid);
            if(user.isEmailVerified()){
                user_info.setText("Email: " + user.getEmail() +"/"+ "Verified");
                verify.setVisibility(View.GONE);
            }
            else{
                user_info.setText("Email: " + user.getEmail() +"/"+ "Not verifie");
                verify.setVisibility(View.VISIBLE);
            }
            if(user.getEmail() == null){
                user_info.setText("Number: " + user.getPhoneNumber() +"/"+ "Verified");
                verify.setVisibility(View.GONE);
            }
        }
        else{
            user_info.setText("Not login");
            verify.setVisibility(View.GONE);
        }
    }
}