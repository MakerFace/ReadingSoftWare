package centerScence.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import centerScence.activity.FragmentHomeTypeBoy;
import centerScence.activity.FragmentHomeTypeGril;
import centerScence.activity.FragmentHomeTypeMagazine;

/**
 * Created by 上官刀刀 on 2018/8/23.
 */

public class HomeTypeViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    public HomeTypeViewPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
        fragments=new ArrayList<>();
        fragments.add(new FragmentHomeTypeBoy());
        fragments.add(new FragmentHomeTypeGril());
        fragments.add(new FragmentHomeTypeMagazine());
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
