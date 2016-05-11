package com.example.lyw.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by LYW on 2016/5/10.
 */
public class CrimeLab {
    private static CrimeLab sCtrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    private CrimeLab(Context appContext){
        mAppContext = appContext;
        mCrimes =new ArrayList<Crime>();
        for (int i = 0;i<100;i++){
            Crime c = new Crime();
            c.setmTitle("Crime #" + i);
            c.setmSoved(i%2==0);
            mCrimes.add(c);
        }
    }

   public static CrimeLab get(Context c){
       if (sCtrimeLab == null){
           sCtrimeLab = new CrimeLab(c.getApplicationContext());
       }
        return sCtrimeLab;
   }

    public ArrayList<Crime> getmCrimes() {
        return mCrimes;
    }

   public Crime getCrime(UUID id){
       for (Crime c :mCrimes) {
           if (c.getmId().equals(id)){
               return c;
           }
       }
       return null;
   }

}
