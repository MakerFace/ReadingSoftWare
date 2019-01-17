package centerScence.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.readingsoftware.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import centerScence.adapter.BookShelfAdapter;
import centerScence.presenter.Book_Shelf_getAllBook_Post;
import centerScence.datamodel.BookBean;
import centerScence.datamodel.EventBookShelfBeans;
import centerScence.datamodel.EventBookShelfUpdate;
import utils.UtilUser;

/**
 * Created by 上官刀刀 on 2018/8/15.
 */

public class FragmentBookShelf extends Fragment {
    private GridView gridView;
    private View viewGoodWord;
    private List<BookBean> bookShelfBeans;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGoodWord = inflater.inflate(R.layout.fragment_bookshelf, container, false);
        initView();
        getData();
        return viewGoodWord;
    }

    /**
     * 初始化view
     */
    void initView() {
        gridView = (GridView) viewGoodWord.findViewById(R.id.book_shelf_gridvew);
        EventBus.getDefault().register(this);
    }

    void getData() {
        Book_Shelf_getAllBook_Post book_shelf_getAllBook_post = new Book_Shelf_getAllBook_Post();
        book_shelf_getAllBook_post.Post(UtilUser.getInstance().getUser().getReaderNo());
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化数据
     */
    void initData() {
        BookShelfAdapter adapter = new BookShelfAdapter(getContext(), bookShelfBeans);
        gridView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventBookShelfBeans event) {
        if (event.bookShelfBeans.size() > 0) {
            bookShelfBeans = event.bookShelfBeans;
            initData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateMessageEventBus(EventBookShelfUpdate event) {
        if (event.updata) {
            getData();
        }
    }
}
