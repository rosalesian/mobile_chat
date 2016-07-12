package com.example.bobster.discount.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bobster.discount.model.Item;
import com.example.bobster.discount.model.User;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by bobster on 2/24/2016.
 */
public class DBAdapter
{
    private static DBHelper helper;
    private static SQLiteDatabase db;
    static Cursor cursor;
    public DBAdapter(Context context)
    {
        helper = new DBHelper(context);
    }

    public void test()
    {
        db = helper.getWritableDatabase();
    }

    public static long add(String table, String nullColumns, ContentValues values)
    {
        open();
        long ifInserted = db.insert(table, nullColumns, values);
        db.close();
        return ifInserted;
    }

    public static int update(String table, ContentValues values, String primaryKey, long id){
        String whereClause = primaryKey + " = ?";
        String [] whereArgs = {id + ""};
        int ifUpdated = db.update(table, values, whereClause, whereArgs);
        return ifUpdated;
    }


    public static int delete(String table, String primaryKey, String id){
        db = helper.getWritableDatabase();
        int ifDeleted = db.delete(table, primaryKey + " = ? ", new String[]{id});
        db.close();
        return ifDeleted;
    }

   /* public int delete(String table, ContentValues values, String primaryKey, long id){
        String whereClause = primaryKey + " = ?";
        String [] whereArgs = {id+""};
        int ifUpdated = db.delete(table, id, whereClause, whereArgs);
        return ifUpdated;
    }*/

   /* public int deleteById(String table, String primaryKey, int idno){
        open();
        String whereClause = primaryKey + " =?";
        String whereArgs[] = {idno+""};
        int ifDeleted = db.deleteById(table, whereClause, whereArgs);
        close();
        return ifDeleted;
    }*/



    //deleteById logical
   /* public  int delete(String table, ContentValues values, String primaryKey, long id){
        String whereClause = primaryKey + " = ?";
        String [] whereArgs = {id+""};
        int ifUpdated = db.update(table, values, whereClause, whereArgs);
        return ifUpdated;
    }*/


    public String view(String table, String [] columns){
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(table, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        int ctr = 0;
        while (cursor.moveToNext() && ctr < cursor.getColumnCount()) {
            cursor.getString(ctr);
            for(int x=0; x < columns.length; x++){
                buffer.append(cursor.getString(x)+ " ") ;
            }
            ctr++;
            buffer.append("\n");
        }
        db.close();
        return buffer.toString();
    }

    public boolean offLogin(String username, String password)
    {
        open();
        String table = User.TABLE_NAME;
        String[] columns = {User.USER_USERNAME, User.USER_PASSWORD};
        String selection = User.USER_USERNAME + " =? AND "+ User.USER_PASSWORD + " =?";
        String[] selectionArgs = {username, password};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        while (cursor.moveToNext())
        {
            if(username.equals(cursor.getString(cursor.getColumnIndex(User.USER_USERNAME))) && password.equals(cursor.getString(cursor.getColumnIndex(User.USER_PASSWORD))))
            {
                close();
                return true;
            }
        }
        close();
        return false;
    }

    public static boolean ifExist(String tableName, String indexName, String indexReference, String name, String reference)
    {
        boolean found = false;
        open();
        String table = tableName;
        String[] columns = {indexName, indexReference};
        String selection = indexName + " =? AND "+ indexReference + " =?";
        String[] selectionArgs = {name, reference};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        while (cursor.moveToNext())
        {
            if(name.equals(cursor.getString(cursor.getColumnIndex(indexName))) && reference.equals(cursor.getString(cursor.getColumnIndex(indexReference))))
            {
                found = true;
            }
            else
            {
                found = false;
            }
        }
        close();
        return found;
    }

    public ArrayList<Item> findByNameItem(String tableName, String indexName, String name)
    {
        ArrayList<Item> list = new ArrayList<>();
        open();
        String table = tableName;
        String[] columns = {indexName};
        String selection = indexName + " =?";
        String[] selectionArgs = {name};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        while (cursor.moveToNext())
        {
            Item item = new Item();
            item.id = cursor.getInt(cursor.getColumnIndex(Item.ITEM_ID));
            item.server_id = cursor.getString(cursor.getColumnIndex(Item.ITEM_SERVER_ID));
            item.name = cursor.getString(cursor.getColumnIndex(Item.ITEM_NAME));
            item.updated_humans = cursor.getString(cursor.getColumnIndex(Item.ITEM_UPDATED_HUMANS));
            item.created_humans = cursor.getString(cursor.getColumnIndex(Item.ITEM_CREATED_HUMANS));
            item.created_at = cursor.getString(cursor.getColumnIndex(Item.ITEM_CREATED_AT));
            item.updated_at = cursor.getString(cursor.getColumnIndex(Item.ITEM_UPDATED_AT));
            list.add(item);
        }
        close();
        return list;
    }

    public long getId(String tableName, String indexName, String name)
    {
        long id = 0;
        open();
        String table = tableName;
        String[] columns = {indexName};
        String selection = indexName + " =?";
        String[] selectionArgs = {name};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        while (cursor.moveToNext())
        {
            Item item = new Item();
            item.id = cursor.getInt(cursor.getColumnIndex(Item.ITEM_ID));
            id = item.id;

        }
        close();
        return id;
    }


    public static void open(){

        //db.setForeignKeyConstraintsEnabled(false);
        db = helper.getWritableDatabase();
       // db.setForeignKeyConstraintsEnabled(true);

    }


    public static void close(){
        helper.close();
    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> userArrayList = new ArrayList<>();
        User user = new User();
        open();
        String table = User.TABLE_NAME;
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        cursor = db.query(table, columns,selection, selectionArgs, groupBy, having,orderBy);
        while(cursor.moveToNext())
        {
            User users = new User();
            users.id = cursor.getInt(cursor.getColumnIndex(User.USER_ID));
            users.username = cursor.getString(cursor.getColumnIndex(User.USER_NAME));
            users.password = cursor.getString(cursor.getColumnIndex(User.USER_PASSWORD));
            users.name = cursor.getString(cursor.getColumnIndex(User.USER_NAME));
            users.email = cursor.getString(cursor.getColumnIndex(User.USER_EMAIL));
            users.server_id = cursor.getString(cursor.getColumnIndex(User.USER_SERVER_ID));
            users.email = cursor.getString(cursor.getColumnIndex(User.USER_EMAIL));
            users.created_humans = cursor.getString(cursor.getColumnIndex(User.USER_CREATED_HUMANS));
            users.updated_humans = cursor.getString(cursor.getColumnIndex(User.USER_UPDATED_HUMANS));
            users.created_at = cursor.getString(cursor.getColumnIndex(User.USER_CREATED_AT));
            users.updated_at = cursor.getString(cursor.getColumnIndex(User.USER_UPDATED_AT));
            userArrayList.add(users);

        }
        close();
        return userArrayList;
    }

    public List<Item> findByIdItem(int id)
    {
        ArrayList<Item> list = new ArrayList<>();
        open();
        String table = Item.TABLE_NAME;
        String [] columns = null;
        //String selection = { id+" = "+id };
        String selection = "id  = ?";
        String[] selectionArgs = {id + ""};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        cursor = db.query(table, columns,selection, selectionArgs, groupBy, having,orderBy);
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
           list.add(item);
        }
        close();
        return list;
    }

    public List<Item> getAllItems()
    {
        List<Item> itemList = new ArrayList<>();

        open();
        String table = Item.TABLE_NAME;
        String [] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = Item.ITEM_CREATED_AT;
        cursor = db.query(table, columns,selection, selectionArgs, groupBy, having,orderBy);
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
    }


    static class DBHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "discount";
        private static final int DATABASE_VERSION = 21;

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
            Log.d("RNL", "database constructor called");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("RNL", "database oncreate called");
            //create table
            db.execSQL(User.CREATE_TABLE_USERS);
            db.execSQL(Item.CREATE_TABLE_ITEMS);


            //triggers
           // db.execSQL(User.USERS_UPDATE_DATE_TRIGGER);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("RNL", "database onupgrade called");

            db.execSQL(User.DROP_TABLE_USERS);
            db.execSQL(Item.DROP_TABLE_ITEMS);
        }
    }
}
