package com.example.readingsoftware.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.readingsoftware.R;
import com.example.readingsoftware.controler.Register_post;
import com.example.readingsoftware.datamodel.EventRegisterResMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by 上官刀刀 on 2018/8/22.
 */

public class LoginRegisterActivity extends AppCompatActivity {
    private Button register_button;
    private EditText username;
    private EditText password1;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void initView() {
        setContentView(R.layout.activity_login_register);
        register_button = (Button) findViewById(R.id.register_button);
        username = (EditText) findViewById(R.id.edit_username);
        password1 = (EditText) findViewById(R.id.edit_password1);
        password2 = (EditText) findViewById(R.id.edit_password2);
        EventBus.getDefault().register(this);
    }

    private void setListener() {
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val_username = String.valueOf(username.getText());
                String val_password1 = String.valueOf(password1.getText());
                String val_password2 = String.valueOf(password2.getText());
                if (val_password1.equals(val_password2)) {
                    Register_post register_post = new Register_post();
                    register_post.Post(val_username, val_password1);
                } else {
                    Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventRegisterResMessage event) {
        if (event.res) {
            Toast.makeText(getApplicationContext(), "注册成功，请登录", Toast.LENGTH_LONG).show();
            Intent to_login_main_activity = new Intent(getApplicationContext(), LoginMainActivity.class);
            startActivity(to_login_main_activity);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
        }
    }
}
