package com.github.xiaogegechen.skinning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.xiaogegechen.library.base.SkinningBaseFragment;

public class TestFragment extends SkinningBaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_test, container, false);
        return view;
    }
}
