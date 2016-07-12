package com.example.bobster.discount.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.bobster.discount.database.DBAdapter;

/**
 * Created by bobster on 12/30/2015.
 */
public class User
{
    public int id;
    public String email;
    public String username;
    public String password;
    public String name;
    public String server_id;
    public String created_at;
    public String updated_at;
    public String created_humans;
    public String updated_humans;
    public boolean delete;
    public String error;

    //table name
    public static final String TABLE_NAME = "users";
    //fields in user
    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CREATED_AT = "created_at";
    public static final String USER_UPDATED_AT = "updated_at";
    public static final String USER_SERVER_ID = "server_id";
    public static final String USER_REFERENCE_ID = "reference_id";
    public static final String USER_DELETED_AT = "deleted_at";
    public static final String USER_CREATED_HUMANS="deleted_humans";
    public static final String USER_UPDATED_HUMANS="updated_humans";

    //CREATE TABLE USERS
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " +TABLE_NAME + " ( "
            +USER_ID    +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
            +USER_USERNAME  +" VARCHAR(255) , "
            +USER_PASSWORD  +" VARCHAR(255) , "
            +USER_NAME  +" VARCHAR(255) , "
            +USER_SERVER_ID  +" VARCHAR(255) , "
            +USER_EMAIL  +" VARCHAR(255) , "
            +USER_CREATED_AT  +" DATETIME DEFAULT CURRENT_TIMESTAMP , "
            +USER_UPDATED_AT  +" DATETIME DEFAULT CURRENT_TIMESTAMP ,  "
            +USER_CREATED_HUMANS  +" VARCHAR(255) , "
            +USER_UPDATED_HUMANS  +" VARCHAR(255) ,"
            +USER_REFERENCE_ID  +" VARCHAR(255) , "
            +USER_DELETED_AT  +" TINYINT(1) );";



    //DROP TABLE USERS
    public static final String DROP_TABLE_USERS =" DROP TABLE IF EXISTS "+TABLE_NAME;


    //update date_modified
    public static final String USERS_UPDATE_DATE_TRIGGER =
            "CREATE TRIGGER person_update_date_trigger" +
                    "  AFTER UPDATE ON " + TABLE_NAME + " FOR EACH ROW" +
                    "  BEGIN " +
                    "UPDATE " + TABLE_NAME +
                    "  SET " + USER_CREATED_AT + " = current_timestamp" +
                    "  WHERE " + USER_ID + " = old." + USER_ID + ";" +
                    "  END";


    public User(int id, String email, String username, String password, String name, String server_id, String created_at, String updated_at, boolean delete, String error, String created_humans, String updated_humans) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.server_id = server_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.delete = delete;
        this.error = error;
        this.created_humans = created_humans;
        this.updated_humans = updated_humans;
    }



    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer_id() {
        return server_id;
    }

    public void setServer_id(String server_id) {
        this.server_id = server_id;
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

    public boolean getDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", server_id='" + server_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", delete='" + delete + '\'' +
                ", error='" + error + '\'' +
                ", created humans='" + created_humans + '\'' +
                ", updated humans='" + updated_humans + '\'' +
                '}';
    }

    public long addUsers(Context context)
    {
        long id = 0;
        DBAdapter dbAdapter;
        dbAdapter = new DBAdapter(context);
        //long id = dbAdapter.add(User.TABLE_NAME, users.getName(), db.);
        ContentValues values = new ContentValues();
        values.put(User.USER_NAME, getName());
        values.put(User.USER_USERNAME, getUsername());
        values.put(User.USER_EMAIL, getEmail());
        values.put(User.USER_PASSWORD, getPassword());
        values.put(User.USER_SERVER_ID, getServer_id());
        values.put(User.USER_CREATED_HUMANS, getCreated_humans());
        values.put(User.USER_UPDATED_HUMANS, getUpdated_humans());
        values.put(User.USER_CREATED_AT, getCreated_at());
        values.put(User.USER_UPDATED_AT, getUpdated_at());
        values.put(User.USER_DELETED_AT, getDelete());
        try
        {
            id = dbAdapter.add(User.TABLE_NAME, User.USER_NAME, values);
            return id;
        }
        catch (Exception es)
        {
            Log.i("DATABASE USER ERROR", es.getMessage());
        }
        return id;
    }


}
