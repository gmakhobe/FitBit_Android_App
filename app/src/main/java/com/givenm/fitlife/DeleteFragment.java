package com.givenm.fitlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class DeleteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle saveInstanceState) {

        View v = inflator.inflate(R.layout.content_delete, container, false);

        // Database code goes here

        return v;
    }
}
