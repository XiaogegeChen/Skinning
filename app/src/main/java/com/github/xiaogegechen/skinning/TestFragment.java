package com.github.xiaogegechen.skinning;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class TestFragment extends Fragment {

    private static final String TAG = Consts.TAG;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean equals = Objects.equals (inflater, getActivity ().getLayoutInflater ());
        Log.d (TAG, equals + "");
        Log.d (TAG, "inflater -> " + inflater);
        Log.d (TAG, "getActivity ().getLayoutInflater () -> " + getActivity ().getLayoutInflater ());
        return super.onCreateView (inflater, container, savedInstanceState);
    }
}
