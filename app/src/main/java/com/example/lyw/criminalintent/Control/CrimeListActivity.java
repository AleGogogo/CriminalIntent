package com.example.lyw.criminalintent.Control;

import android.support.v4.app.Fragment;

/**
 * Created by LYW on 2016/5/10.
 */
public class CrimeListActivity extends SingleFragmentActivity{


    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
