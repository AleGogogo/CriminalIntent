package com.example.lyw.criminalintent.Control;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import com.example.lyw.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by LYW on 2016/5/14.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.example.lyw.DatePickerFragment.date";
    private Date mDate;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //得到crimeFragment传来的数据
        mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
        //代码清单12-7 创建Calender对象，
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dailog_xml, null);

        DatePicker datePicker = (DatePicker) v.findViewById(R.id
                .dailog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {


            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                mDate = new GregorianCalendar(year,month,day).getTime();

                getArguments().putSerializable(EXTRA_DATE,mDate);

            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_piker_title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult(Activity.RESULT_OK);
                    }
                })
                .create();

    }
    public static DatePickerFragment newInstance(Date date){
       Bundle args =  new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void setResult(int resultCode){
        if (getTargetFragment() == null){
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE,mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
