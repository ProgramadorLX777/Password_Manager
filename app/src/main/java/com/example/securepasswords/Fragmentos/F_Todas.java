package com.example.securepasswords.Fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.securepasswords.OpcionesPassword.AgregarPassword;
import com.example.securepasswords.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class F_Todas extends Fragment {

    FloatingActionButton agregarPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f__todas, container, false);

        agregarPassword = view.findViewById(R.id.agregarPassword);

        agregarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AgregarPassword.class));

                /*Intent intent = new Intent(getActivity(), AgregarPassword.class);
                startActivity(intent);*/
            }
        });

        return view;
    }
}