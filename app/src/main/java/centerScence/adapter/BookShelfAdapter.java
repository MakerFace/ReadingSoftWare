package centerScence.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readingsoftware.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import centerScence.presenter.Book_Shelf_Item_ClickListener;
import centerScence.datamodel.BookBean;

/**
 * Created by 上官刀刀 on 2018/8/20.
 */

public class BookShelfAdapter extends BaseAdapter {
    private List<BookBean> bookShelfBeens;
    private Context context;

    public BookShelfAdapter(Context context, List<BookBean> bookShelfBeens) {
        this.bookShelfBeens = bookShelfBeens;
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_main_book_shelf, null);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.home_item_img);
            holder.name = (TextView) view.findViewById(R.id.good_word_item_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(getItem(i).getBookName());
        Log.i("message:","http://10.25.42.14:8080/web/" + getItem(i).getBookImage());
        Picasso.with(context).load("http://10.25.42.14:8080/web/" + getItem(i).getBookImage()).into(holder.img);
        setItemListener(view, getItem(i).getBookNo(), getItem(i).getBookName());
        return view;
    }

    /**
     * 设置item的监听事件
     */
    void setItemListener(View view, String id, String name) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        view.setOnClickListener(new Book_Shelf_Item_ClickListener(view.getContext(), id, name));
    }

    class ViewHolder {
        ImageView img;
        TextView name;
    }
}
