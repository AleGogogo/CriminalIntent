package com.example.lyw.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by LYW on 2016/5/9.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSoved;

    public boolean ismSoved() {
        return mSoved;
    }

    public void setmSoved(boolean mSoved) {
        this.mSoved = mSoved;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();

    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
