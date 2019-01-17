package centerScence.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.readingsoftware.R;
import java.util.List;

/**
 * Created by 上官刀刀 on 2018/8/22.
 */

public class ReadViewPagerAdapter extends PagerAdapter{
    private List<String> texts;
    private List<View> views;
    private String title;
    public ReadViewPagerAdapter(List<View> views,List<String> texts,String title)
    {
        this.texts =texts;
        this.views=views;
        this.title=title;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=views.get(position);
        TextView title_textView= (TextView) view.findViewById(R.id.title);
        TextView content_textView= (TextView) view.findViewById(R.id.content);
        TextView pager= (TextView) view.findViewById(R.id.pager);
        title_textView.setText(title);
        content_textView.setText(texts.get(position));
        String pager_str=String.valueOf(position+1)+"/"+String.valueOf(views.size());
        pager.setText(pager_str);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
