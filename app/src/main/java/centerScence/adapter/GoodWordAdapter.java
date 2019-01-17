package centerScence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readingsoftware.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import centerScence.activity.ReadGoodwordActivity;
import centerScence.datamodel.GoodWordBean;

/**
 * Created by 上官刀刀 on 2018/8/20.
 */

public class GoodWordAdapter extends BaseAdapter {
    private List<GoodWordBean> goodWordBeens;
    private Context context;

    public GoodWordAdapter(Context context, List<GoodWordBean> goodWordBeens) {
        this.goodWordBeens = goodWordBeens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return goodWordBeens.size();
    }

    @Override
    public GoodWordBean getItem(int i) {
        return goodWordBeens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_main_good_word, null);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.home_item_img);
            holder.name = (TextView) view.findViewById(R.id.good_word_item_title);
          holder.writter = (TextView) view.findViewById(R.id.good_word_item_writter);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText("标题:"+getItem(i).getEssayTitle());
        holder.writter.setText("作者:"+getItem(i).getEssayWritter());
        Picasso.with(context).load("http://10.25.42.14:8080/web/" + getItem(i).getEssayImage()).into(holder.img);
        final int temp=i;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_read_good_word_activity=new Intent(context, ReadGoodwordActivity.class);
                to_read_good_word_activity.putExtra("id",getItem(temp).getEssayNo());
                to_read_good_word_activity.putExtra("name",getItem(temp).getEssayTitle());
                context.startActivity(to_read_good_word_activity);
            }
        });
        return view;
    }


    class ViewHolder {
        ImageView img;
        TextView name;
        TextView writter;
    }
}
