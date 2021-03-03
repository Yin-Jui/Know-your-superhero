package com.example.knowyoursuperhero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class SecondFragment extends Fragment {

    Button save;
    EditText userName;
    TextView hero;
    ImageView image;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        save = view.findViewById(R.id.save);
        userName = view.findViewById(R.id.userName);
        hero = view.findViewById(R.id.hero);
        image = view.findViewById(R.id.heroImage);
       // String name = Utility.Companion.checkLogin();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseFirestore.getInstance().collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener() {
                public final void onComplete(@NotNull Task task) {
                    Intrinsics.checkParameterIsNotNull(task, "task");
                    if (task.isSuccessful()) {
                        DocumentSnapshot snapshot = (DocumentSnapshot) task.getResult();
                        Users user = snapshot != null ? (Users) snapshot.toObject(Users.class) : null;
                        if (user != null) {
                            userName.setText(user.getUsername());
                            hero.setText(user.getHero());
                            if(!user.getImageUrl().equals(""))
                                Picasso.get().load(user.getImageUrl()).into(image);
                        }
                    }

                }
            });
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.Companion.setName(userName.getText().toString());
                Snackbar.make(view, "Successfully saved!", Snackbar.LENGTH_LONG).show();
            }
        });

        view.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}