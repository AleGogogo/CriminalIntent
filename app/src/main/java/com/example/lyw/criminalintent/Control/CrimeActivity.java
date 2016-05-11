package com.example.lyw.criminalintent.Control;

import android.support.v4.app.Fragment;


public class CrimeActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
