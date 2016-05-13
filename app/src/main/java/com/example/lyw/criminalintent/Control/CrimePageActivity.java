package com.example.lyw.criminalintent.Control;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.lyw.criminalintent.R;
import com.example.lyw.criminalintent.model.Crime;
import com.example.lyw.criminalintent.model.CrimeLab;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by LYW on 2016/5/12.
 */
public class CrimePageActivity extends FragmentActivity {
    private ViewPager mViewPage;
    private ArrayList<Crime> mCrimes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPage = new ViewPager(this);
        mViewPage.setId(R.id.viewPager);
        setContentView(mViewPage);
        mCrimes = CrimeLab.get(this).getmCrimes();

        //代码清单11-4 设置Pager adapter
        FragmentManager fm = getSupportFragmentManager();
        mViewPage.setAdapter(new FragmentPagerAdapter(fm) {

            @Override
            public Fragment getItem(int position) {
                Crime c = mCrimes.get(position);
                return CrimeFragment.newInstance(c.getmId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        //代码清单11-7 添加OnPageChangeListener监听器
        mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime crime = mCrimes.get(position);
                if (crime.getmTitle() != null) {
                    setTitle(crime.getmTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //11-6初始化分页显示（避免点击crime 分页万年不变
        UUID mUid = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0 ;i<mCrimes.size();i++) {
            if (mCrimes.get(i).getmId().equals(mUid)) {

                mViewPage.setCurrentItem(i);
                break;
            }
        }
    }

}
