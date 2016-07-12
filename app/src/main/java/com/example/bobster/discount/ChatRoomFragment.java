package com.example.bobster.discount;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobster.discount.chatroom.InsideChatroom;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRoomFragment extends Fragment {

    EditText txt_username;
    TextView error_text;
    Button btnJoin;
    public ChatRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_room, container, false);
        txt_username = (EditText) view.findViewById(R.id.username_input);
        error_text = (TextView) view.findViewById(R.id.id_error_text);
        btnJoin = (Button) view.findViewById(R.id.sign_in_button);
        ClickJoin();
        return view;


    }


    private void ClickJoin() {
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                if (username.equals("")) {
                    error_text.setText("Please Input User name");
                } else {
                    txt_username.setText("");
                   /* InsideChatroom fragment = new InsideChatroom();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();*/
                    InsideChatroom fragment = new InsideChatroom();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                }


                //Toast.makeText(getActivity(), "User name is "+username, Toast.LENGTH_LONG).show();
            }
        });
    }


}
