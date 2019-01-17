package com.example.readingsoftware.controler;

import android.support.annotation.NonNull;

import com.example.readingsoftware.datamodel.EventLoginResMessage;
import com.example.readingsoftware.datamodel.User;
import com.google.gson.Gson;

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

public class Login_post {
    private UtilHttpPost login_post = new UtilHttpPost();

    public void Post(final String username, final String password) {
        FormBody formBody = new FormBody.Builder().add("username", username).add("password", password).build();
        Call login_call = login_post.doPost(UtilHttpPost.URL + "LoginServlet", formBody);
        login_call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                EventBus.getDefault().post(new EventLoginResMessage(false));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseMessage = response.body().string();
                if(responseMessage.equals("0"))
                {
                    EventBus.getDefault().post(new EventLoginResMessage(false));
                }
                else
                {
                    successCallback(responseMessage);
                    EventBus.getDefault().post(new EventLoginResMessage(true));
                }
            }
        });
    }

    private void successCallback(String response)
    {
        User user=new Gson().fromJson(response,User.class);
        UtilUser.getInstance().setUser(user);
    }
}
