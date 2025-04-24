package com.ak222102468.webstmik_tablayout;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private moodleFragment _moodleFragment = new moodleFragment();
    private Context _context;
    private krsstmikFragment _krsstmikFragment = new krsstmikFragment();
    private int _tabCount;
    private webstmikFragment _webstmikFragment = new webstmikFragment();

    public MyFragmentPagerAdapter(Context context, FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);

        _context = context;
        _tabCount = tabCount;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return _moodleFragment;
            case 1:
                return _krsstmikFragment;
            case 2:
                return _webstmikFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return _tabCount;
    }
}
