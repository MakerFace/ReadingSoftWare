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

import centerScence.adapter.GoodWordAdapter;
import centerScence.presenter.Good_Word_getAllword_Post;
import centerScence.datamodel.EventGoodwordBeans;
import centerScence.datamodel.GoodWordBean;

/**
 * Created by 上官刀刀 on 2018/8/15.
 */

public class FragmentGoodword extends Fragment {
    private ListView good_word_listview;
    private View view;
    private List<GoodWordBean> goodWordBeens;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewGoodWord=inflater.inflate(R.layout.fragment_goodword,container,false);
        view=viewGoodWord;
        initView();
        getdata();
        return viewGoodWord;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    void initView()
    {
        good_word_listview= (ListView) view.findViewById(R.id.good_word_listview);
        EventBus.getDefault().register(this);
    }

    void getdata()
    {
        Good_Word_getAllword_Post good_word_getAllword_post=new Good_Word_getAllword_Post();
        good_word_getAllword_post.Post();
    }
    void initdata()
    {
        GoodWordAdapter goodWordAdapter=new GoodWordAdapter(getContext(),goodWordBeens);
        good_word_listview.setAdapter(goodWordAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventGoodwordBeans event) {
        goodWordBeens = event.goodWordBeens;
        initdata();
    }
}
