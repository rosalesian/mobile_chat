package com.example.bobster.discount.chatroom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bobster.discount.R;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsideChatroom extends Fragment {

    EditText txt_message;
    ImageButton btn_send_button;

    public InsideChatroom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inside_chatroom, container, false);
        txt_message = (EditText) view.findViewById(R.id.message_input);
        btn_send_button = (ImageButton) view.findViewById(R.id.send_button);
        sendMessage();
        return view;
    }

    public void sendMessage() {
        btn_send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = txt_message.getText().toString();
                //Toast.makeText(getActivity(), "Hello Message " + message, Toast.LENGTH_LONG).show();
                if(message.equals("")) {

                }
                else {
                    updateView(message);
                    txt_message.setText("");
                }

            }
        });



    }

    public void updateView(String message) {
        ArrayList<Card> cards = new ArrayList<Card>();
        //get items object
        //Item classItem = itemList.get(i);
        // Create a Card
        Card card = new Card(getActivity());
        // Create a CardHeader
        CardHeader header = new CardHeader(getActivity());
        // Add Header to card
        // header.setTitle("Angry bird: " + classItem.getName());
        header.setTitle("Ian Rosales");
        card.setTitle(message);
        card.addCardHeader(header);

        CardThumbnail thumb = new CardThumbnail(getActivity());
        thumb.setDrawableResource(R.drawable.rosales_logo);
        card.addCardThumbnail(thumb);

        cards.add(card);

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        CardListView listView = (CardListView) getActivity().findViewById(R.id.myList);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

}
