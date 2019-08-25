package com.github.xiaogegechen.library.base;

import androidx.fragment.app.Fragment;

import com.github.xiaogegechen.library.Skinning;

public class SkinningBaseFragment extends Fragment {

    @Override
    public void onDestroyView() {
        Skinning.INSTANCE.removeView (getActivity (), getView ());
        super.onDestroyView ();
    }
}
