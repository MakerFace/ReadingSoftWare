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
import centerScence.presenter.Home_getMagazineBook_Post;
import centerScence.datamodel.BookBean;
import centerScence.datamodel.EventMagazineBookBeans;

/**
 * Created by 上官刀刀 on 2018/8/15.
 */

public class FragmentHomeTypeMagazine extends Fragment {
    private List<BookBean> bookShelfBeens;
    private ListView magazine_listview;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewHomeMagazine=inflater.inflate(R.layout.fragment_home_type_magazine,container,false);
        initView(viewHomeMagazine);
        getdata();
        return viewHomeMagazine;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    void initView(View viewGoodWord)
    {
        magazine_listview = (ListView) viewGoodWord.findViewById(R.id.magazine_listview);
        EventBus.getDefault().register(this);
    }

    void getdata()
    {
        Home_getMagazineBook_Post home_getMagazineBook_post=new Home_getMagazineBook_Post();
        home_getMagazineBook_post.Post("magazine");
    }
    void initdata()
    {
        HomeTypeBookAdapter homeTypeBookAdapter =new HomeTypeBookAdapter(getContext(),bookShelfBeens);
        magazine_listview.setAdapter(homeTypeBookAdapter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventMagazineBookBeans event) {
        bookShelfBeens = event.bookShelfBeens;
        initdata();
    }
}
