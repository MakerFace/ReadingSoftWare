package centerScence.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readingsoftware.R;

import centerScence.adapter.HomeTypeViewPagerAdapter;

/**
 * Created by 上官刀刀 on 2018/8/15.
 */

public class FragmentHome extends Fragment {
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewHome=inflater.inflate(R.layout.fragment_home,container,false);
        viewPager= (ViewPager) viewHome.findViewById(R.id.viewPager);
        HomeTypeViewPagerAdapter homeTypeViewPagerAdapter=new HomeTypeViewPagerAdapter(getFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(homeTypeViewPagerAdapter);
        return viewHome;
    }
}
