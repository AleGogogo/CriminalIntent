package com.example.lyw.criminalintent.Control;

import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.lyw.criminalintent.R;
import com.example.lyw.criminalintent.model.Crime;

import java.util.List;

/**
 * Created by LYW on 2016/5/10.
 */
public class CrimeListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);




    }
    //代码清单 9-14 创建CrimeAdapter内部类
   private class CrimeAdapter extends ArrayAdapter<Crime>{
        public CrimeAdapter(ArrayAdapter<Crime> crimes){
            super(getActivity(),0, (List<Crime>) crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView)
            return super.getView(position, convertView, parent);
        }
    }


}
