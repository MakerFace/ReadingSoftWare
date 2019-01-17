package com.example.readingsoftware.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.readingsoftware.R;

import centerScence.activity.MainActivity;

public class LoginAcitvity extends AppCompatActivity {

    private TextView guest;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
        setViewListener();
    }

    /**
     * 初始化界面
     */
    private void initview()
    {
        setContentView(R.layout.activity_login);
        guest= (TextView) findViewById(R.id.guest);
        login= (Button) findViewById(R.id.login);
        register= (Button) findViewById(R.id.register);
    }

    void setViewListener()
    {

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_main_activity=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(to_main_activity);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_loginMain_activity=new Intent(getApplicationContext(),LoginMainActivity.class);
                startActivity(to_loginMain_activity);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_login_register_activity=new Intent(getApplicationContext(),LoginRegisterActivity.class);
                startActivity(to_login_register_activity);
                finish();
            }
        });
    }

}
