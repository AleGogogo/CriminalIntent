package com.example.lyw.criminalintent.Control;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lyw.criminalintent.R;
import com.example.lyw.criminalintent.model.Crime;
import com.example.lyw.criminalintent.model.CrimeLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LYW on 2016/5/10.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;
    private static final int REQUEST_CRIME = 1;
    private boolean mSubtitleVisible;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        //Ϊʲô���get()�����ﴫ�����getActivity��������getcontext;
        mCrimes = CrimeLab.get(getActivity()).getmCrimes();
        CrimeAdapter crimeAdapter =new CrimeAdapter(mCrimes);
        setListAdapter(crimeAdapter);

        //code list 16-4 inform FragmentManager its fragment should receive onCreateOptionMenu
       setHasOptionsMenu(true);

        //16-17 save CrimeListFragment instance
        setRetainInstance(true);
        mSubtitleVisible = false;

    }

    // <code> 16-19
    //     according to the value of mSubtitleVisible to setting subtitle</code>
    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        Log.d("TAG","excute ?");
       if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            if (mSubtitleVisible){
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }
        return v;
    }

    //代码清单10-1 点击某个crime启动对应的CrimeActivity
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
       Log.d("TAG",c.getmTitle()+"was clicked");
//
        //代码清单嵥11-  crimePageActivity
        Intent i =new Intent(getActivity(),CrimePageActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getmId());
        startActivityForResult(i,REQUEST_CRIME);

    }

    //�����嵥 9-14 ����CrimeAdapter�ڲ���
   private class CrimeAdapter extends ArrayAdapter<Crime>{

        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,  crimes);
        }

        @Override
        public Crime getItem(int position) {
            return mCrimes.get(position);
        }

        @Override
        public int getCount() {
            return mCrimes.size();
        }


        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_itme_crime,null);
            }
            Crime c = getItem(position);
            TextView title = (TextView) convertView.findViewById(R.id.id_item_title);
            title.setText(c.getmTitle());
            TextView content = (TextView) convertView.findViewById(R.id.id_item_time);
            content.setText(c.getmDate()+"");
            CheckBox mSolved = (CheckBox) convertView.findViewById(R.id.id_checkbox);
//            mSolved.setChecked(c.ismSoved());
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CRIME){

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

   //code list 16-7 coding method to request choosing menu task
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_crime:
                Crime crime =new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent i = new Intent(getActivity(),CrimePageActivity.class);
               //0 stand for requestCode
                startActivityForResult(i,0);
            case R.id.menu_item_show_subtitle:
                // code list 16-16 achieve switch menu tile
                 //incomplete
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
                default:
                    return super.onOptionsItemSelected(item);
        }
        }



}
