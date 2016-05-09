package com.example.lyw.criminalintent;

import java.util.UUID;

/**
 * Created by LYW on 2016/5/9.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    
    public Crime(){
        mId = UUID.randomUUID();
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
