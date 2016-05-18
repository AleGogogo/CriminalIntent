package com.example.lyw.criminalintent.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * this class is used to realize saving date and reading data from file
 * Created by LYW on 2016/5/18.
 */
public class CrimeIntentJsonSerializer {
    private Context mContext;
    private String mFileName;

    public CrimeIntentJsonSerializer(Context mContext, String mFileName) {
        this.mContext = mContext;
        this.mFileName = mFileName;
    }

    //<code>
    // 17-4 create file and writing data
    // </code>
    public void saveCrimes(ArrayList<Crime> crimes)
            throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Crime c : crimes) {
            jsonArray.put(c.toJson());
        }
        OutputStreamWriter osw = null;
        try {
            OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);

            osw = new OutputStreamWriter(out);
            osw.write(jsonArray.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //<code>
    // 17-5  reading date from file
    // </code>
    public ArrayList<Crime> loadCrimes() throws JSONException, IOException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
       StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            InputStream in =  mContext.openFileInput(mFileName);
            InputStreamReader isr = new InputStreamReader(in);
             br = new BufferedReader(isr);
            String str = null;
            while ((str =br.readLine())!=null){
                sb.append(str);
            }
            // parse the Json using JSonTokener
            JSONArray array =(JSONArray) new JSONTokener(sb.toString())
                    .nextValue();
            for (int i=0;i<array.length();i++){
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                br.close();
            }
        }
        return crimes;
    }
}

