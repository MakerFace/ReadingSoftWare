package centerScence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.readingsoftware.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;

import centerScence.adapter.ReadViewPagerAdapter;
import centerScence.presenter.Book_Shelf_getContent_post;
import centerScence.datamodel.EventBookShelfConent;

/**
 * Created by 上官刀刀 on 2018/8/20.
 */

public class ReadActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<String> texts;
    private List<View> views;
    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    void initView() {
        setContentView(R.layout.activity_read);
        viewPager = (ViewPager) findViewById(R.id.read_viewpager);
        EventBus.getDefault().register(this);
    }

    void getData() {
        Intent from_book = getIntent();
        id = from_book.getStringExtra("id");
        name = from_book.getStringExtra("name");
        Book_Shelf_getContent_post book_shelf_getContent_post = new Book_Shelf_getContent_post();
        book_shelf_getContent_post.Post(id);
    }

    void initData() {
        views = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < texts.size(); i++)
            views.add(inflater.inflate(R.layout.item_read_viewpager, null, false));
        ReadViewPagerAdapter readViewPagerAdapter = new ReadViewPagerAdapter(views, texts, name);
        viewPager.setAdapter(readViewPagerAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventBookShelfConent event) {
        texts = event.texts;
        initData();
    }
}
