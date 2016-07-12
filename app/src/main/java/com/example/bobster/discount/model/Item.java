package com.example.bobster.discount.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.bobster.discount.database.DBAdapter;
import com.example.bobster.discount.provider.DataProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by bobster on 1/14/2016.
 */
public class Item implements Serializable, DataProvider
{
    public int id;
    public String server_id;
    public String reference_id;
    public String user_id;
    public String name;
    public boolean deleted_at;
    public String created_at;
    public String updated_at;
    public String created_humans;
    public String updated_humans;


    public static final String itemPreferences = "itemData";

    //TABLE NAME
    public static final String TABLE_NAME = "items";
    //FIELDS
    public static final String ITEM_ID = "id";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_USER_ID = "user_id";
    public static final String ITEM_UPDATED_HUMANS = "updated_humans";
    public static final String ITEM_CREATED_HUMANS = "created_humans";
    public static final String ITEM_CREATED_AT = "created_at";
    public static final String ITEM_UPDATED_AT = "updated_at";
    public static final String ITEM_SERVER_ID = "server_id";
    public static final String ITEM_DELETED_AT = "deleted_at";
    public static final String ITEM_REFERENCE = "reference_id";

    //CREATE TABLE ITEMS
    public static final String CREATE_TABLE_ITEMS = "CREATE TABLE " + TABLE_NAME + " ( "
            +ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
            +ITEM_SERVER_ID + " INTEGER , "
            +ITEM_REFERENCE + " INTEGER , "
            +ITEM_NAME + " VARCHAR(225) NOT NULL , "
            +ITEM_USER_ID + " INTEGER , "
            +ITEM_CREATED_HUMANS + " VARCHAR(255) , "
            +ITEM_UPDATED_HUMANS + " VARCHAR(255) , "
            +ITEM_CREATED_AT + " DATETIME CURRENT_TIMESTAMP , "
            +ITEM_UPDATED_AT + " DATETIME  CURRENT_TIMESTAMP, "
            +ITEM_DELETED_AT + " TINYINT(1) "
            +");";

    public static final String DROP_TABLE_ITEMS = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //update date_modified
 /*   public static final String ITEM_UPDATE_DATE_TRIGGER =
            "CREATE TRIGGER person_update_date_trigger" +
                    "  AFTER UPDATE ON " + TABLE_NAME + " FOR EACH ROW" +
                    "  BEGIN " +
                    "UPDATE " + TABLE_NAME +
                    "  SET " + USER_CREATED_AT + " = current_timestamp" +
                    "  WHERE " + USER_ID + " = old." + USER_ID + ";" +
                    "  END";*/

    public Item(int id, String server_id, String user_id, String name, boolean deleted_at, String created_at, String updated_at, String created_humans, String updated_humans) {
        this.id = id;
        this.server_id = server_id;
        this.user_id = user_id;
        this.name = name;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_humans = created_humans;
        this.updated_humans = updated_humans;
    }


    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(boolean deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getServer_id() {
        return server_id;
    }

    public void setServer_id(String server_id) {
        this.server_id = server_id;
    }

    public String getCreated_humans() {
        return created_humans;
    }

    public void setCreated_humans(String created_humans) {
        this.created_humans = created_humans;
    }

    public String getUpdated_humans() {
        return updated_humans;
    }

    public void setUpdated_humans(String updated_humans) {
        this.updated_humans = updated_humans;
    }
    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", server_id='" + server_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", created_humans='" + created_humans + '\'' +
                ", updated_humans='" + updated_humans + '\'' +
                '}';
    }

    public long addItems(Context context)
    {
        long id=0;
        DBAdapter dbAdapter = new DBAdapter(context);
        ContentValues values = new ContentValues();
        values.put(Item.ITEM_NAME, getName());
        values.put(Item.ITEM_REFERENCE, getReference_id());
        values.put(Item.ITEM_SERVER_ID, getServer_id());
        values.put(Item.ITEM_USER_ID, getUser_id());
        values.put(Item.ITEM_UPDATED_HUMANS, getUpdated_humans());
        values.put(Item.ITEM_CREATED_HUMANS, getCreated_humans());
        values.put(Item.ITEM_CREATED_AT, getCreated_at());
        values.put(Item.ITEM_UPDATED_AT, getUpdated_at());
        values.put(Item.ITEM_DELETED_AT, getDeleted_at());

       try
       {
           id = dbAdapter.add(Item.TABLE_NAME, Item.ITEM_NAME, values);
           return id;
       }
       catch (Exception es)
       {
           Log.w("DATABASE ERROR", es.getMessage());
       }

        return id;
    }

    public long add()
    {
        return DBAdapter.add(TABLE_NAME, ITEM_NAME, getContentValues());
    }

    public boolean find(String name, String reference, Context context)
    {
        DBAdapter dbAdapter = new DBAdapter(context);
        boolean found = false;
        if(dbAdapter.ifExist(Item.TABLE_NAME, Item.ITEM_NAME, name, Item.ITEM_SERVER_ID, reference))
        {
            found = true;
        }
        return found;
    }

    public List<Item> findById()
    {
        List<Item> itemList = null;

        return itemList;
    }

    public long delete(Context context, String id)
    {
        DBAdapter dbAdapter = new DBAdapter(context);
        long idResult = 0;

        try {
            idResult = dbAdapter.delete(Item.TABLE_NAME, Item.ITEM_ID, id);
            Log.i("DELETED", idResult+"");

        }
        catch (Exception es)
        {

        }
        return idResult;
    }

    public ArrayList<Item> findName(String name, Context context)
    {
        ArrayList<Item> list = new ArrayList<>();
        DBAdapter dbAdapter = new DBAdapter(context);

        list = dbAdapter.findByNameItem(TABLE_NAME, ITEM_NAME, name);

        return list;
    }

    public long getItemId(String name, Context context)
    {

        long id = 0;

        DBAdapter dbAdapter = new DBAdapter(context);
        try
        {
            id = dbAdapter.getId(Item.TABLE_NAME, Item.ITEM_NAME, name);
        }
        catch(Exception es)
        {
            Log.i("DATABASE ERROR", es.getMessage());
        }
       return id;

    }

    @Override
    public ContentValues getContentValues() {

        ContentValues values = new ContentValues();
        values.put(Item.ITEM_NAME, getName());
        values.put(Item.ITEM_SERVER_ID, getServer_id());
        values.put(Item.ITEM_REFERENCE, getReference_id());
        values.put(Item.ITEM_USER_ID, getUser_id());
        values.put(Item.ITEM_UPDATED_HUMANS, getUpdated_humans());
        values.put(Item.ITEM_CREATED_HUMANS, getCreated_humans());
        values.put(Item.ITEM_CREATED_AT, getCreated_at());
        values.put(Item.ITEM_UPDATED_AT, getUpdated_at());
        values.put(Item.ITEM_DELETED_AT, getDeleted_at());
        return values;
    }

    @Override
    public long offlineAdd(Context context) {
        return DBAdapter.add(TABLE_NAME, ITEM_NAME, getContentValues());
    }

    @Override
    public boolean onlineAdd() {
        return false;
    }

    @Override
    public int onlineUpdate(long idno) {
        return 0;
    }

    @Override
    public int onlineDelete(long idno) {
        return 0;
    }

    @Override
    public long offlineAdd() {
        return 0;
    }

    @Override
    public int offlineUpdate(long idno) {
        return 0;
    }

    @Override
    public int offlineDelete(long idno) {
        return 0;
    }

    @Override
    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

   /* public List<Item> getAllItems(Context context)
    {
        DBAdapter dbAdapter = new DBAdapter(context);
        List<Item> itemList = new ArrayList<>();
        Cursor cursor;
        String table = Item.TABLE_NAME;
        String [] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        cursor = dbAdapter.query(table, columns,selection, selectionArgs, groupBy, having,orderBy);
        while(cursor.moveToNext())
        {
            Item item = new Item();
            item.id = cursor.getInt(cursor.getColumnIndex(Item.ITEM_ID));
            item.server_id = cursor.getString(cursor.getColumnIndex(Item.ITEM_SERVER_ID));
            item.name = cursor.getString(cursor.getColumnIndex(Item.ITEM_NAME));
            item.updated_humans = cursor.getString(cursor.getColumnIndex(Item.ITEM_UPDATED_HUMANS));
            item.created_humans = cursor.getString(cursor.getColumnIndex(Item.ITEM_CREATED_HUMANS));
            item.created_at = cursor.getString(cursor.getColumnIndex(Item.ITEM_CREATED_AT));
            item.updated_at = cursor.getString(cursor.getColumnIndex(Item.ITEM_UPDATED_AT));
            itemList.add(item);
        }
        close();
        return itemList;
    }*/
}
