package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by humberto on 20/11/16.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab = null;
    private List<Crime> mCrimes;

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<Crime>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved((i & 1) == 0); // Every other one
            mCrimes.add(crime);
        }
    }

    private static void createInstance(Context context) {
        if (sCrimeLab == null) {
            synchronized(CrimeLab.class) {
                if (sCrimeLab == null) {
                    sCrimeLab = new CrimeLab(context);
                }
            }
        }
    }

    public static CrimeLab getInstance(Context context) {
        if (sCrimeLab == null)
            createInstance(context);

        return sCrimeLab;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
