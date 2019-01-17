package centerScence.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import centerScence.datamodel.EventInsertBookShelf;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import utils.UtilHttpPost;

/**
 * Created by 上官刀刀 on 2018/8/21.
 */

public class Home_Insert_Into_bookShelf_Post {
    private UtilHttpPost login_post = new UtilHttpPost();

    public void Post(String bookId, String userId) {
        FormBody formBody = new FormBody.Builder().add("bookid", bookId).add("userid", userId).build();
        Call login_call = login_post.doPost("http://10.25.42.14:8080/web/InsertReader_BookServlet", formBody);
        login_call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EventBus.getDefault().post(new EventInsertBookShelf(false));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseMessage = response.body().string();
                if (responseMessage.equals("1"))
                    EventBus.getDefault().post(new EventInsertBookShelf(true));
                else if (responseMessage.equals("0"))
                    EventBus.getDefault().post(new EventInsertBookShelf(false));
            }
        });
    }
}
