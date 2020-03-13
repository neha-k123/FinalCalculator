package com.kulshreshthaneha.finalcalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//@SuppressLint("ValidFragment")
public class FirstFragment extends Fragment {
    TextView textView;

    public FirstFragment(){
        //empty constuctor
    }
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment, container, false);
//        textView = view.findViewById(R.id.fragment1);
//        textView.setText("first");
//        return textView;

        return inflater.inflate(R.layout.fragment, container, false);
    }
}