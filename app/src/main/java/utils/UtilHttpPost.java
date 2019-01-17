package utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 上官刀刀 on 2018/8/21.
 */

public class UtilHttpPost {

    public static String URL = "http://10.25.42.14:8080/web/";

    public Call doPost(String url, FormBody formBody)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().post(formBody).url(url).build();
        return client.newCall(request);
    }
}
