package com.example.bobster.discount.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bobster.discount.CustomerFragment;
import com.example.bobster.discount.ItemFragment;
import com.example.bobster.discount.R;
import com.example.bobster.discount.api.RestClient;
import com.example.bobster.discount.model.Item;

import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddItemFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    List<Response> responseList;
    EditText txt_name, txt_reference;
    Button btn_add, btn_cancel;
    private static String name;
    private static String reference;
    private static String date;
    NavigationView navigationView = null;
    Toolbar toolbar = null;


    public AddItemFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* txt_name = (EditText) getActivity().findViewById(R.id.iten_name);
        txt_reference = (EditText) getActivity().findViewById(R.id.iten_reference);

        btn_add = (Button) getActivity().findViewById(R.id.btn_additen);
        btn_cancel = (Button) getActivity().findViewById(R.id.btn_cancel);*/

       // btn_add.setOnClickListener(this);


       // btn_add.setOnClickListener(this);
        //btn_cancel.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_item, container, false);
        txt_name = (EditText) view.findViewById(R.id.iten_name);
        txt_reference = (EditText) view.findViewById(R.id.iten_reference);

        btn_add = (Button) view.findViewById(R.id.btn_additen);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

        Long tsLong = System.currentTimeMillis()/1000;
        date = tsLong.toString();
        Log.i("DATE", date);
        addItems();
        clearText();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Toolbar mToolbar = null;
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //getActivity().setActionBar(view.mToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void addItems()
    {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);*/
                Item item = new Item();
                if(item.isOnline(getActivity()))
                {
                    name = txt_name.getText().toString();
                    reference = txt_reference.getText().toString();

                    if (name.equals("") && reference.equals(""))
                    {
                        Toast.makeText(getActivity(), "Name and Reference Required!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        //ONLINE
                        RestClient.get().addItem(name, reference, new Callback<com.example.bobster.discount.model.Response>() {
                            @Override
                            public void success(com.example.bobster.discount.model.Response response, Response response2) {
                                Log.e("error", response.toString());
                                offlineAdd(response.getId());
                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {

                            }
                        });

                    }
                }
                //OFFLINE
                else
                {
                    Toast.makeText(getActivity(), "Please Check Your Connection!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void clearText()
    {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_name.setText("");
                txt_reference.setText("");

                ItemFragment fragment = new ItemFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();

            }
        });
    }

    public void offlineAdd(String server_id)
    {
        Log.i("DATE", date);
        Item item = new Item();
        item.setName(name);
        item.setServer_id(server_id);
        item.setReference_id(reference);
        item.setCreated_at(date);
        item.setUpdated_at(date);
        item.setDeleted_at(false);
        try
        {
            long id = item.offlineAdd(getActivity());

            Toast.makeText(getActivity(), "Name "+name+" Reference "+reference+" Date "+date+" last id "+id, Toast.LENGTH_LONG).show();
            ItemFragment fragment = new ItemFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
        catch (Exception es)
        {

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //Set the frament initially
            CustomerFragment fragment = new CustomerFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            Log.i("result", "*******************************************");

        } else if (id == R.id.nav_gallery) {
            //Set the frament initially
            ItemFragment fragment = new ItemFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.id_logout) {
            getActivity().finish();
        }


        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
