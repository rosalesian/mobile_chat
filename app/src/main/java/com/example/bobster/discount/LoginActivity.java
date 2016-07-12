package com.example.bobster.discount;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobster.discount.api.RestClient;
import com.example.bobster.discount.database.DBAdapter;
import com.example.bobster.discount.model.Item;
import com.example.bobster.discount.model.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    DBAdapter dbAdapter;
    ContentValues values;
    EditText txt_username, txt_password;
    TextView txt_error;
    Button btn_login;
    List<User> userList;
    Context context;
    List<Item> listItem;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        txt_username = (EditText) this.findViewById(R.id.id_username);
        txt_password = (EditText) this.findViewById(R.id.id_password);
        txt_error = (TextView) this.findViewById(R.id.id_error);

        btn_login = (Button) this.findViewById(R.id.id_login);

        btn_login.setOnClickListener(this);
        dbAdapter = new DBAdapter(context);
        try
        {
            Log.d("DATABASE TEST", "test");
            dbAdapter.test();
        }
        catch (Exception ex)
        {
            Log.d("DATABASE ERROR", ex.getMessage());
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        String username = txt_username.getText().toString();
        String password = txt_password.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


        //ONLINE LOGIN
     /*   Item item = new Item();
        if(item.isOnline(this))
        {
            RestClient.get().Login(username, password, new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    OnlineLogin(user);
                }

                @Override
                public void failure(RetrofitError retrofitError) {

                }
            });
        }
        else
        {
            Toast.makeText(this, "Please Check Your Connection!", Toast.LENGTH_LONG).show();
            txt_error.setText("Please Check Your Connection!");
        }
*/

       /* if(username.equals("") && password.equals(""))
        {
            txt_error.setText("Field id Required!");
        }
        else
        {
            OfflineLogin(username, password);
        }*/



    }

    public void OnlineLogin(User users) {
        // User user = new User();

        Log.i("USERS", users.toString());
        if (users.getError().equals("false")) {

            User user = users;

            try
            {
                boolean found = dbAdapter.ifExist(User.TABLE_NAME, User.USER_NAME, User.USER_SERVER_ID, user.getName(), user.getServer_id());
                if(!found)
                {
                    long id = user.addUsers(this);
                    Log.i("DATABASE SUCCESS!", id+"");
                }

            }
            catch (Exception es)
            {
                Log.i("DATABASE ERROR", es.getMessage());
            }
            //ADD ITENS
            addItems();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            txt_username.setText("");
            txt_password.setText("");
        } else {
            txt_username.setText("");
            txt_password.setText("");
            // Toast.makeText(this, "Username "+username+" Password "+password, Toast.LENGTH_LONG).show();

            txt_error.setText("Username and Password Not Exist!");
        }

    }

    public void OfflineLogin(String username, String password)
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String date = year+"-"+month+"-"+day;
        //ADD USER
        User user = new User();
        user.setName("rosales");
        user.setUsername("discount");
        user.setPassword("rosales");
        user.setEmail("gso8412@gmail.com");
        user.setDelete(false);

        if(!dbAdapter.offLogin(username, password))
        {
            try
            {
                long id = user.addUsers(this);
                if(id == 0)
                {
                    Toast.makeText(this,"Username and Password not match ", Toast.LENGTH_LONG).show();
                    txt_error.setText("Username and Password Not Exist!");
                }
                else
                {


                }
            }
            catch (Exception es)
            {
                Toast.makeText(this,"Error "+es.getMessage(), Toast.LENGTH_LONG).show();
            }
        }


        //OFFLINE LOGIN
        boolean found = dbAdapter.offLogin(username, password);
        if(found)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            txt_username.setText("");
            txt_password.setText("");
        }
        else
        {
            Toast.makeText(this,"Username and Password not match ", Toast.LENGTH_LONG).show();
            txt_error.setText("Username and Password Not Exist!");
        }
    }

    public void addItems()
    {
        RestClient.get().getItem(new Callback<List<Item>>() {
            @Override
            public void success(List<Item> itemList, Response response) {
                listItem = itemList;
                // updateItems(listItem);
                updateItems();

            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }

    public void updateItems()
    {
        DBAdapter dbAdapter = new DBAdapter(this);
        for(int i = 0; i< listItem.size(); i++)
        {
            Item item = new Item();
            item = listItem.get(i);
            try
            {
                boolean found = dbAdapter.ifExist(Item.TABLE_NAME, Item.ITEM_NAME, Item.ITEM_REFERENCE, listItem.get(i).getName(), listItem.get(i).getReference_id());
                if(!found)
                {
                    long id = item.add();
                   // Log.i("DATABASE SUCCESS!", id+"");
                }
            }
            catch (Exception es)
            {
                Log.i("DATABASE ERROR", es.getMessage());
            }
        }



    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.bobster.discount/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.bobster.discount/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


}
