package com.example.attendance.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.attendance.R;
import com.example.attendance.View.Main;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

   public Button signout;
    FirebaseAuth firebaseAuth;

    Activity activity;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        signout= view.findViewById(R.id.signOut);
        signout.setOnClickListener(this);

        activity=getActivity();

        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null)
        {
            getActivity().finish();
            startActivity(new Intent(activity,Main.class));
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==signout){
            firebaseAuth.signOut();
            activity.finish();
            startActivity(new Intent(activity,Main.class));
        }


    }
}
