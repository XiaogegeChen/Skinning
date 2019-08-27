package com.github.xiaogegechen.library.base;

import androidx.fragment.app.Fragment;

import com.github.xiaogegechen.library.Skinning;

/**
 * Fragment基类，使用者可以直接继承SkinningBaseFragment，
 * 或者在自己的Fragment中
 * onDestroyView()方法中调用：
 * Skinning.INSTANCE.removeView (getActivity (), getView ());
 * 去组中删除这部分view
 */
public class SkinningBaseFragment extends Fragment {

    @Override
    public void onDestroyView() {
        Skinning.INSTANCE.removeView (getActivity (), getView ());
        super.onDestroyView ();
    }
}
