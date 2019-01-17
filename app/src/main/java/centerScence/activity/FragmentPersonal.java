package centerScence.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readingsoftware.R;
import com.squareup.picasso.Picasso;

import utils.UtilUser;

/**
 * Created by 上官刀刀 on 2018/8/15.
 */

public class FragmentPersonal extends Fragment {
    private View view;
    private ImageView imageView;
    private TextView name;
    private TextView id;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewGoodWord=inflater.inflate(R.layout.fragment_personal,container,false);
        view=viewGoodWord;
        initView();
        initdata();
        return viewGoodWord;
    }

    void initView()
    {
        imageView= (ImageView) view.findViewById(R.id.img);
        name= (TextView) view.findViewById(R.id.name);
        id= (TextView) view.findViewById(R.id.id);
        name.setText("用户："+UtilUser.getInstance().getUser().getReaderName());
        id.setText("读书号:"+UtilUser.getInstance().getUser().getReaderNo());
    }

    void initdata()
    {
        Picasso.with(getContext()).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1934952861,3453579486&fm=27&gp=0.jpg").into(imageView);
    }
}
