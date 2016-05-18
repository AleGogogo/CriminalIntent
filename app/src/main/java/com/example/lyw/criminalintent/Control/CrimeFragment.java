package com.example.lyw.criminalintent.Control;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import java.util.Date;
import java.util.UUID;

/**
 * Created by LYW on 2016/5/9.
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.example.lyw.criminalintent.crime_id";
    private static final String DAILOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private Crime mCrime;
    private EditText mTitle;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
        //10-7 ��argument�����ȡ����
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        //���ʣ�ɵɵ�ֲ���  10-5 �� 10-7 ��˳��

        // //code list 16-9 inform FragmentManager its fragment should receive onCreateOptionMenu
          setHasOptionsMenu(true);
    }

    //�����嵥10-5  ��fragment��������Ӹ�activityǰ���
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
     //code list 16-8 invoking up navigation button
       if (testVersion(Build.VERSION.SDK_INT,Build.VERSION_CODES.HONEYCOMB)){
           getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
       }





        mTitle = (EditText) v.findViewById(R.id.id_title);
        mTitle.setText(mCrime.getmTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i,
                                          int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int
                    i1, int i2) {
                mCrime.setmTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDateButton = (Button) v.findViewById(R.id.id_date_button);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //用datepickerFragment的newinstance方法来替换构造方法生成DatePickerFragment
                //将crimeFragment中的数据传给datepickerFragment
                DatePickerFragment dailog = DatePickerFragment
                        .newInstance(mCrime.getmDate());
                //将CrimeFragment.this设置成 DatePickerFragment的目标Fragment
                dailog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dailog.show(fm,DAILOG_DATE);
            }

        });
        String time = DateFormat.getDateInstance(DateFormat.FULL).format(mCrime.getmDate());
        mDateButton.setText(time);
//        mDateButton.setEnabled(false);


        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.id_checkbox);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton,
                                         boolean b) {
                mCrime.setmSoved(b);
            }
        });

        return v;
    }


    /**
     * @param Version   Build.VERSION.SDK_INT stands for Android device version
     * @param v Build.VERSION_CODES.HONEYCOMB stands for Andorid one of system version
     * @return
     */
    private   Boolean testVersion(int Version,int v) {
        if (Version >= v){
            if (NavUtils.getParentActivityName(getActivity())!=null)
           return true;
        }
        return false;
    }



    //从datepickerFragment的extra中获取日期，设置对应的crime记录日期，然后刷新按钮的显示
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode != Activity.RESULT_OK)return;
        if (requestCode == REQUEST_DATE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setmDate(date);
            updateDate();
        }

    }
   public void updateDate(){
       mDateButton.setText(mCrime.getmDate().toString());
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //using the Class UavUtils to testing metadata
                if (NavUtils.getParentActivityName(getActivity())!=null){
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity()).saveCrimes();
    }
}
