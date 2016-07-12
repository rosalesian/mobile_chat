package com.example.bobster.discount.provider;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by bobster on 3/17/2016.
 */
public interface DataProvider {
    public ContentValues getContentValues();

    public long offlineAdd(Context context);

    public boolean onlineAdd();

    public int onlineUpdate(long idno);

    public int onlineDelete(long idno);

    public long offlineAdd();

    public int offlineUpdate(long idno);

    public int offlineDelete(long idno);

    public boolean isOnline(Context context);
}
