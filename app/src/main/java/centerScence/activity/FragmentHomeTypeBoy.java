package centerScence.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.readingsoftware.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import centerScence.adapter.HomeTypeBookAdapter;
import centerScence.presenter.Home_getBoyBook_Post;
import centerScence.datamodel.BookBean;
import centerScence.datamodel.EventBoyBookBeans;

/**
 * Created by 上官刀刀 on 2018/8/15.
 */

public class FragmentHomeTypeBoy extends Fragment {
    private List<BookBean> bookShelfBeens;
    private ListView boy_listview;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewGoodWord=inflater.inflate(R.layout.fragment_home_type_boy,container,false);
        initView(viewGoodWord);
        getdata();
        return viewGoodWord;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    void initView(View viewGoodWord)
    {
        boy_listview= (ListView) viewGoodWord.findViewById(R.id.boy_listview);
        EventBus.getDefault().register(this);
    }

    void getdata()
    {
        Home_getBoyBook_Post home_getBoyBook_post=new Home_getBoyBook_Post();
        home_getBoyBook_post.Post("boy");
    }
    void initdata()
    {
        HomeTypeBookAdapter homeTypeBookAdapter =new HomeTypeBookAdapter(getContext(),bookShelfBeens);
        boy_listview.setAdapter(homeTypeBookAdapter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventBoyBookBeans event) {
        bookShelfBeens = event.bookShelfBeens;
        initdata();
    }
}
