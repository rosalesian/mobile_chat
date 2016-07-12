package com.example.bobster.discount;


import android.app.ActionBar;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.bobster.discount.api.RestClient;
import com.example.bobster.discount.controller.AddItemFragment;
import com.example.bobster.discount.controller.ItemDetailsFragment;
import com.example.bobster.discount.database.DBAdapter;
import com.example.bobster.discount.model.Item;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements Card.OnCardClickListener{

    private List<Item> itemList;
    private static DBAdapter dbAdapter;
    private static Context context;
   /* private AutoCompleteTextView Text;*/
  /*  String[] itemData = {"Cebu","Bohol","Mactan"};*/
    ArrayList<String> dbData;

    NavigationView navigationView = null;
    Toolbar mToolbar = null;

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private AutoCompleteTextView edtSeach;

    String[] itemData ={"cebu", "iligan", "cagayan"};


    public ItemFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
        dbAdapter = new DBAdapter(context);



        try
        {
            Log.d("DATABASE TEST", "test");
            dbAdapter.test();
            itemList = dbAdapter.getAllItems();
            //itemData = new String[itemList.size()];


        }
        catch (Exception ex)
        {
            Log.d("DATABASE ERROR", ex.getMessage());
        }



       /* autoCompleteTextViewItem = (AutoCompleteTextView) getActivity().findViewById(R.id.idAutoCompleteItem);
        itemData = getResources().getStringArray(R.array.item_sample);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemData );
        autoCompleteTextViewItem.setAdapter(adapter);*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //getItems();
       // Log.i("testing", "Hello World");
        return inflater.inflate(R.layout.fragment_item, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Log.i("testing", itemList.toString());
        //getItems();
        //List<Item> itemList;
        //ONLINE TRANSACTION
      /*  RestClient.get().getItem(new Callback<List<Item>>() {
            @Override
            public void success(List<Item> items, Response response) {
                itemList = items;
                updateView();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i("Error Retrofir", retrofitError.getMessage());
            }
        });*/

      // Log.i("RESULT", itemList.toString());

        /*int listImages[] = new int[]{R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};

        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i<10; i++) {
            //get items object
            //Item classItem = itemList.get(i);
            // Create a Card
            Card card = new Card(getActivity());
            // Create a CardHeader
            CardHeader header = new CardHeader(getActivity());
            // Add Header to card
            // header.setTitle("Angry bird: " + classItem.getName());
            header.setTitle("Angry bird: " + "Testing");
            card.setTitle("sample title");
            card.addCardHeader(header);

            CardThumbnail thumb = new CardThumbnail(getActivity());
            thumb.setDrawableResource(listImages[2]);
            card.addCardThumbnail(thumb);

            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        CardListView listView = (CardListView) view.findViewById(R.id.myList);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }*/




       // OFFLINE
        updateView();

    }

    public void getItems()
    {
        RestClient.get().getItem(new Callback<List<Item>>() {
            @Override
            public void success(List<Item> items, Response response) {
                itemList = items;
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i("Error Retrofir", retrofitError.getMessage());
            }
        });
    }

    public void updateView()
    {


       /* for (int i = 0; i < itemList.size(); i++)
        {
            itemData[i] = itemList.get(i).getName();
        }*/



        ContentValues values = new ContentValues();
      // Log.i("RESULT", itemList.size()+"");
        int listImages[] = new int[]{R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};

       // dbAdapter.getAllItems();
        Item classItem = new Item();
        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i<itemList.size(); i++) {
            //get items object
            classItem = itemList.get(i);

          // itemData[i] = itemList.get(i).getName();
           // items = classItem;
            // Create a Card
            Card card = new Card(getActivity());
            // Create a CardHeader
            CardHeader header = new CardHeader(getActivity());
            // Add Header to card
            header.setTitle(classItem.getName());
            //header.setTitle("Angry bird: " + "Testing");
            card.setTitle("REFERENCE ID : "+classItem.getServer_id()+"\nCREATED AT : "+classItem.getCreated_at()+"\nUPDATED AT : "+classItem.getUpdated_at());
            //card.setTitle("REFERENCE ID "+classItem.getServer_id());
           // card.setTitle("CREATED AT "+ classItem.getCreated_at());

            card.addCardHeader(header);
            card.setOnClickListener(this);
            card.setType(i);

            CardThumbnail thumb = new CardThumbnail(getActivity());
           // thumb.setDrawableResource(R.drawable.item_files);
            //card.addCardThumbnail(thumb);

            cards.add(card);
        }

        //add the Item in the class
        //classItem.addItems(getActivity());

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        CardListView listView = (CardListView) getActivity().findViewById(R.id.myList);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }

        // Log.i("DATA", itemData.toString()+"");
        //itemData = getResources().getStringArray(R.array.item_sample);

    }

    @Override
    public void onClick(Card card, View view) {
        Item item = itemList.get(card.getType());
        addPreferences(item);
        Log.w("ITEM CLICK", item.toString());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.options_menu, menu);
        mSearchAction = menu.findItem(R.id.action_search);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if(item.getItemId() == R.id.id_addItems)
        {

            AddItemFragment fragment = new AddItemFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }*/
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                AddItemFragment fragment = new AddItemFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                return true;
            case R.id.action_search:
                handleMenuSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void handleMenuSearch(){
        android.support.v7.app.ActionBar action = ((AppCompatActivity) getActivity()).getSupportActionBar(); //get the actionbar

       // ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle("Your Title");
        //toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
       // ActionBar action = getActivity().setSu; //get the actionbar



      if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(true); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar



            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.search));

            edtSeach = (AutoCompleteTextView)action.getCustomView().findViewById(R.id.edtSearch);

            //doSearch(name);

          /*  ArrayAdapter<String> adapter = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_dropdown_item_1line, itemData);

            edtSeach.setThreshold(3);
            edtSeach.setAdapter(adapter);*/

           String name = edtSeach.getText().toString();
           doSearch(name);



            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            edtSeach = (AutoCompleteTextView)action.getCustomView().findViewById(R.id.edtSearch); //the text editor

         /* Text = (AutoCompleteTextView) getActivity().findViewById(R.id.id_complete_item);
          ArrayAdapter<String> adapter = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_dropdown_item_1line, itemData);
          Text.setThreshold(3);
          Text.setAdapter(adapter);*/

            //this is a listener to do a search when the user clicks on search button
          /*  edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    Log.i("RESULT EVENT", event.toString());
                    Log.i("RESULT ACTION", actionId+"");
                    Log.i("RESULT VIEW", String.valueOf(v.findViewById(R.id.edtSearch)));
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        String searchname = edtSeach.getText().toString();
                        Log.i("RESULT CLICK", searchname);
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });*/

        /*  edtSeach.setOnEditorActionListener(new AutoCompleteTextView.OnEditorActionListener() {
              @Override
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                  Log.i("RESULT EVENT", event.toString());
                  Log.i("RESULT ACTION", actionId+"");
                  Log.i("RESULT VIEW", String.valueOf(v.findViewById(R.id.edtSearch)));
                  return false;
              }
          });*/


            edtSeach.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.search));
            isSearchOpened = true;
        }
    }
    private void doSearch(String name) {

        Log.i("NAME", name);
        Item item = new Item();
       try
       {
           long id = dbAdapter.getId(Item.TABLE_NAME, Item.ITEM_NAME, name);
           Log.i("RESULT ITEM",id+"");
       }
       catch (Exception es) {
           Log.i("ITEM RESULT", es.getMessage());
       }
       // addPreferences(item);
    }


    public void addPreferences(Item item)
    {
        Item items = item;
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(Item.itemPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       // editor.putString(Item.ITEM_NAME, items.getName());

        editor.putInt(Item.ITEM_ID, items.getId());
        editor.putString(Item.ITEM_SERVER_ID, items.getServer_id());
        editor.apply();

        ItemDetailsFragment fragment = new ItemDetailsFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }



  /*  @Override
    public void onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        super.onPrepareOptionsMenu(menu);
    }*/
}
