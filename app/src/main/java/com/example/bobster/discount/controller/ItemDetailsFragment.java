package com.example.bobster.discount.controller;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobster.discount.CustomerFragment;
import com.example.bobster.discount.ItemFragment;
import com.example.bobster.discount.R;
import com.example.bobster.discount.api.RestClient;
import com.example.bobster.discount.database.DBAdapter;
import com.example.bobster.discount.model.Item;
import com.example.bobster.discount.model.Response;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailsFragment extends Fragment {

    List<Item> itemList;
    static int id;
    static String server_id;
    NavigationView navigationView = null;
    Toolbar toolbar = null;

    public ItemDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_details, container, false);



        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Item.itemPreferences, Context.MODE_PRIVATE);
        id  = sharedPreferences.getInt(Item.ITEM_ID, 0);
        server_id = sharedPreferences.getString(Item.ITEM_SERVER_ID, null);
        //int itemId = Integer.parseInt(id);


        Log.i("RESULT DETAULS", id + "");

        DBAdapter dbAdapter = new DBAdapter(getActivity());
        itemList = dbAdapter.findByIdItem(id);

        Log.i("RESULT DETAULS", itemList.toString());
        getItem(itemList);
    }



    public void getItem(List<Item> list)
    {


        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i<list.size(); i++) {
            //get items object
            Item classItem = itemList.get(i);


            // items = classItem;
            // Create a Card
            Card card = new Card(getActivity());
            // Create a CardHeader
            CardHeader header = new CardHeader(getActivity());
            // Add Header to card
            header.setTitle("NAME : "+classItem.getName());
            //header.setTitle("Angry bird: " + "Testing");
            card.setTitle("REFERENCE ID : "+classItem.getServer_id()+"\nCREATED AT : "+classItem.getCreated_at()+"\nUPDATED AT : "+classItem.getUpdated_at());
            //card.setTitle("REFERENCE ID "+classItem.getServer_id());
            // card.setTitle("CREATED AT "+ classItem.getCreated_at());

            card.addCardHeader(header);



            CardThumbnail thumb = new CardThumbnail(getActivity());
            thumb.setDrawableResource(R.drawable.gallery);
            card.addCardThumbnail(thumb);

            cards.add(card);
        }

        //add the Item in the class
        //classItem.addItems(getActivity());

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        CardListView listView = (CardListView) getActivity().findViewById(R.id.myList);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();;
        inflater.inflate(R.menu.edit_delete, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.id_edit_menu)
        {
            Toast.makeText(getActivity(), "EDIT menu", Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId() == R.id.id_delete_menu)
        {
            Item item1 = new Item();
            if(item1.isOnline(getActivity()))
            {
                RestClient.get().deleteItem(server_id, new Callback<Response>() {
                    @Override
                    public void success(Response response, retrofit.client.Response response2) {
                        deletItem(response.getMessage());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {

                    }
                });
            }
            else
            {
                Toast.makeText(getActivity(), "Check Your Internet Connection", Toast.LENGTH_LONG).show();
            }


        }
        return super.onOptionsItemSelected(item);
    }


    public void deletItem(String message)
    {
        Item item1 = new Item();
        item1.delete(getActivity(), id + "");
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        ItemFragment fragment = new ItemFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
