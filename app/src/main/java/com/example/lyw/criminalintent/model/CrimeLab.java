package com.example.lyw.criminalintent.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by LYW on 2016/5/10.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;
    private CrimeIntentJsonSerializer mSerializer;
    private static final String FILENAME = "crime.json";


    //<code>
    // 17-6
    // modify this method :create mSerializer,before creating mCrimes,loadCrimes first
    // </code>
    private CrimeLab(Context appContext){
        mAppContext = appContext;
        mSerializer =new CrimeIntentJsonSerializer(mAppContext,FILENAME);

        try {
            mSerializer.loadCrimes();
        } catch (Exception e){
            mCrimes =new ArrayList<Crime>();
            for (int i = 0;i<100;i++){
                Crime c = new Crime();
                c.setmTitle("Crime #" + i);
                c.setmSoved(i%2==0);
                mCrimes.add(c);
            }
            Log.d("TAG","Error loading crimes: ",e);
        }
    }

   public static CrimeLab get(Context c){
       if (sCrimeLab == null){
           sCrimeLab = new CrimeLab(c.getApplicationContext());
       }
        return sCrimeLab;
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
  public void addCrime(Crime c){
      mCrimes.add(c);
  }

    //<code>
    // 17-7
    // </code>
    public boolean saveCrimes(){
        try {
            mSerializer.saveCrimes(mCrimes);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
