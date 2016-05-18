package com.example.lyw.criminalintent.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by LYW on 2016/5/9.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_DATE = "date";
    private static final String JSON_SOLVED = "solved";


    //<code>
    // 17-2 the Constructor method:reading Crime data from file
    // </code>
    public Crime(JSONObject jsonObject) throws JSONException {
        mId = UUID.fromString(jsonObject.getString(JSON_ID));
        mDate = new Date(jsonObject.getString(jsonObject.getString(JSON_DATE)));
        if (jsonObject.has(JSON_TITLE)){
            mTitle =jsonObject.getString(JSON_TITLE);
        }
        mSolved = jsonObject.getBoolean(JSON_SOLVED);
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSoved(boolean mSoved) {
        this.mSolved = mSoved;
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

    //<code>
    // 17-1 realizing the Crime convert to Json format
    // </code>
    public JSONObject toJson() throws JSONException {
        JSONObject jsonobject =new JSONObject();
        jsonobject.put(JSON_ID,mId);
        jsonobject.put(JSON_DATE,mDate);
        jsonobject.put(JSON_TITLE,mTitle);
        jsonobject.put(JSON_SOLVED,mSolved);
        return jsonobject;
    }
}
