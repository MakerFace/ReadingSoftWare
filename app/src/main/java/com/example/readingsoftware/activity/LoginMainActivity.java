package com.example.readingsoftware.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.readingsoftware.R;
import com.example.readingsoftware.controler.Login_post;
import com.example.readingsoftware.datamodel.EventLoginResMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import centerScence.activity.MainActivity;

/**
 * Created by 上官刀刀 on 2018/8/21.
 */

public class LoginMainActivity extends AppCompatActivity {
    private Button login;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    void initView() {
        setContentView(R.layout.activity_login_main);
        login = (Button) findViewById(R.id.button_login);
        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_password);
        EventBus.getDefault().register(this);
    }

    void setListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val_username = String.valueOf(username.getText());
                String val_password = String.valueOf(password.getText());
                Login_post login_post = new Login_post();
                login_post.Post(val_username, val_password);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventLoginResMessage event){
        if(event.res) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
            Intent to_main_activity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(to_main_activity);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
        }
    }
}
