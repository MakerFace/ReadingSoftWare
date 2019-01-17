package com.example.readingsoftware.controler;

import com.example.readingsoftware.datamodel.EventLoginResMessage;
import com.example.readingsoftware.datamodel.EventRegisterResMessage;
import com.example.readingsoftware.datamodel.User;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import utils.UtilHttpPost;
import utils.UtilUser;

/**
 * Created by 上官刀刀 on 2018/8/21.
 */

public class Register_post {

    private String username;
    private String password;
    private UtilHttpPost login_post = new UtilHttpPost();

    public void Post(final String username, final String password) {
        this.username=username;
        this.password=password;
        FormBody formBody = new FormBody.Builder().add("username", username).add("password", password).build();
        Call login_call = login_post.doPost("http://10.25.42.14:8080/web/RegisterServlet", formBody);
        login_call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EventBus.getDefault().post(new EventRegisterResMessage(false));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseMessage = response.body().string();
                if(responseMessage.equals("1"))
                {
                    EventBus.getDefault().post(new EventRegisterResMessage(true));
                }
                else if(responseMessage.equals("0"))
                {
                    EventBus.getDefault().post(new EventRegisterResMessage(false));
                }
            }
        });
    }
}
