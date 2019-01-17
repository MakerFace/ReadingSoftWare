package centerScence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.readingsoftware.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import centerScence.presenter.Home_Insert_Into_bookShelf_Post;
import centerScence.datamodel.BookBean;
import centerScence.datamodel.EventBookShelfUpdate;
import centerScence.datamodel.EventInsertBookShelf;
import utils.UtilUser;

/**
 * Created by 上官刀刀 on 2018/8/20.
 */

public class HomeTypeBookAdapter extends BaseAdapter {
    private List<BookBean> bookShelfBeens;
    private Context context;

    public HomeTypeBookAdapter(Context context, List<BookBean> bookShelfBeens) {
        this.bookShelfBeens = bookShelfBeens;
        this.context = context;
        EventBus.getDefault().register(this);
    }

    @Override
    public int getCount() {
        return bookShelfBeens.size();
    }

    @Override
    public BookBean getItem(int i) {
        return bookShelfBeens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_main_home_book, null);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.home_item_img);
            holder.name = (TextView) view.findViewById(R.id.good_word_item_title);
            holder.add_book= (TextView) view.findViewById(R.id.add_book);
            holder.price= (TextView) view.findViewById(R.id.home_item_rice);
            holder.publisher= (TextView) view.findViewById(R.id.home_item_publisher);
            holder.writter= (TextView) view.findViewById(R.id.good_word_item_writter);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText("书名:"+getItem(i).getBookName());
        Picasso.with(context).load("http://10.25.42.14:8080/web/" + getItem(i).getBookImage()).into(holder.img);
        holder.price.setText("价格:"+getItem(i).getBookPrice());
        holder.writter.setText("作者:"+getItem(i).getBookWritter());
        holder.publisher.setText("出版社:"+getItem(i).getBookPublisher());
        setItemListener(holder.add_book, getItem(i).getBookNo(), UtilUser.getInstance().getUser().getReaderNo());
        return view;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 设置item的监听事件
     */
    void setItemListener(View view, final String bookid, final String userid) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home_Insert_Into_bookShelf_Post home_insert_into_bookShelf_post=new Home_Insert_Into_bookShelf_Post();
                home_insert_into_bookShelf_post.Post(bookid,userid);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(EventInsertBookShelf event) {
        if(event.res)
        {
            Toast.makeText(context,"加入成功",Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new EventBookShelfUpdate(true));
        }
        else
        {
            Toast.makeText(context,"加入失败",Toast.LENGTH_SHORT).show();
        }
    }

    class ViewHolder {
        ImageView img;
        TextView name;
        TextView add_book;
        TextView price;
        TextView publisher;
        TextView writter;
    }
}
