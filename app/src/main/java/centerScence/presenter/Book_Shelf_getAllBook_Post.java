package centerScence.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import centerScence.datamodel.BookBean;
import centerScence.datamodel.EventBookShelfBeans;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import utils.UtilHttpPost;

/**
 * Created by 上官刀刀 on 2018/8/21.
 */

public class Book_Shelf_getAllBook_Post {
    private UtilHttpPost login_post = new UtilHttpPost();

    public void Post(String id) {
        FormBody formBody = new FormBody.Builder().add("id", id).build();
        Call login_call = login_post.doPost("http://10.25.42.14:8080/web/Reader_BookServlet", formBody);
        login_call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EventBus.getDefault().post(new EventBookShelfBeans(new ArrayList<BookBean>()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseMessage = response.body().string();
                Log.i("message1:",responseMessage);
                Type type = new TypeToken<ArrayList<BookBean>>(){}.getType();
                ArrayList<BookBean> bookShelfBeens=new Gson().fromJson(responseMessage,type);
                EventBus.getDefault().post(new EventBookShelfBeans(bookShelfBeens));
            }
        });
    }
}
