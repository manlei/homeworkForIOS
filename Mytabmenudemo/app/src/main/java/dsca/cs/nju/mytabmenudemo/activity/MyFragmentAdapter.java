package dsca.cs.nju.mytabmenudemo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by manlei on 2017/4/6.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> list;
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
