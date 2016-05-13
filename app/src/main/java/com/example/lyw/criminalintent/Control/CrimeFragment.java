package com.example.lyw.criminalintent.Control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.lyw.criminalintent.R;
import com.example.lyw.criminalintent.model.Crime;
import com.example.lyw.criminalintent.model.CrimeLab;

import java.text.DateFormat;
import java.util.UUID;

/**
 * Created by LYW on 2016/5/9.
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.example.lyw.criminalintent.crime_id";
    private Crime mCrime;
    private EditText mTitle;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
        //10-7 从argument里面获取数据
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        //疑问，傻傻分不清  10-5 与 10-7 的顺序
    }

    //代码清单10-5  在fragment创建后，添加给activity前完成
   public static CrimeFragment newInstance(UUID mUid){
       Bundle bundle = new Bundle();
       bundle.putSerializable(EXTRA_CRIME_ID,mUid);
       CrimeFragment fragment =new CrimeFragment();
       fragment.setArguments(bundle);
       return fragment;
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime,container,false);

        mTitle = (EditText) v.findViewById(R.id.id_title);
        mTitle.setText(mCrime.getmTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i,
                                          int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setmTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDateButton = (Button) v.findViewById(R.id.id_date_button);
        String time = DateFormat.getDateInstance(DateFormat.FULL).format(mCrime.getmDate());
        mDateButton.setText(time);
        mDateButton.setEnabled(false);


        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.id_checkbox);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setmSoved(b);
            }
        });
        return v;
    }

}
