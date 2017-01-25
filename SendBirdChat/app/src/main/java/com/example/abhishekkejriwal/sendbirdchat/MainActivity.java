package com.example.abhishekkejriwal.sendbirdchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;
import com.sendbird.android.UserListQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    Button chat;
    EditText editText;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.create);
        editText = (EditText) findViewById(R.id.email);
        chat = (Button) findViewById(R.id.chat);
        button.setOnClickListener(this);
        chat.setOnClickListener(this);
        SendBird.init("0255F2FF-382A-45B4-B1FB-9AC0332FCDFC", this);
    }

    @Override public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.create:
                SendBird.connect(editText.getText().toString(), new SendBird.ConnectHandler() {
                    @Override
                    public void onConnected(User user, SendBirdException e) {
                        if (e != null) {
                            return;
                        } else {
                            Toast.makeText(MainActivity.this, "User created", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.chat:
                List<String> userIdList = new ArrayList<>();
                userIdList.add("abhi.kejriwal1508@gmail.com");
                userIdList.add("yoshi@test.com");
                GroupChannel.createChannelWithUserIds(userIdList, true, new GroupChannel.GroupChannelCreateHandler() {
                    @Override
                    public void onResult(GroupChannel groupChannel, SendBirdException e) {
                        if (e != null) {
                            return;
                        } else {
                            Toast.makeText(MainActivity.this, "Channel created", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
        }
    }
}
