package com.example.lyw.criminalintent.Control;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        //为什么这个get()方法里传入的是getActivity，而不是getcontext;
        mCrimes = CrimeLab.get(getActivity()).getmCrimes();
        CrimeAdapter crimeAdapter =new CrimeAdapter(mCrimes);
        setListAdapter(crimeAdapter);

    }

    //代码清单10-1 当用户点击某条crime，启动CrimeActivity
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
       Log.d("TAG",c.getmTitle()+"was clicked");
//
        //代码清单11-  启动crimePageActivity
        Intent i =new Intent(getActivity(),CrimePageActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getmId());
        startActivity(i);

    }

    //代码清单 9-14 创建CrimeAdapter内部类
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
}
