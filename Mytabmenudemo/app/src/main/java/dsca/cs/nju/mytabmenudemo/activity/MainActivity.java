package dsca.cs.nju.mytabmenudemo.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.classification.ClassificationFragment;
import dsca.cs.nju.mytabmenudemo.home.HomeFragment;
import dsca.cs.nju.mytabmenudemo.notification.NotificationFragment;
import dsca.cs.nju.mytabmenudemo.util.AppContext;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    private ImageView userAvator;

    private TextView userName;

    private TextView userAccount;

    private FragmentTabHost mtabhost;

    private LayoutInflater inflater;

    private List<Fragment> list = new ArrayList<Fragment>();

    private Class fragmentArray[] = {HomeFragment.class,ClassificationFragment.class,NotificationFragment.class};

    private int imageviewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_view_btn,R.drawable.tab_notification_btn};

    private String textviewArray[] = {"首页","分类","消息"};

    private ViewPager vp;

    private DrawerLayout mDrawerLayout;

    //widget of the navigation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        initpage();

        userAccount.setText(AppContext.getOnlineUser().getAccount());

    }


    /**
     * 初始化菜单
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START,true);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 初始化界面
     */
    private void initview(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_dish);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            // actionBar.setHomeAsUpIndicator(0);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //设置Drawlayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);

        //找到headview
        View headerView = navigationView.getHeaderView(0);

        userAvator = (ImageView) headerView.findViewById(R.id.iv_nav_header_avator);

        userName = (TextView) headerView.findViewById(R.id.tv_nav_header_user_name);

        userAccount = (TextView) headerView.findViewById(R.id.tv_nav_header_email);

        Log.e("main:",userAccount.toString());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(MenuItem item){
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //返回的时候才能填充mDrawlayout

        //userAccount.setText(AppContext.getOnlineUser().getAccount());

        vp = (ViewPager)findViewById(R.id.viewpager);
        vp.addOnPageChangeListener(this);
        inflater = LayoutInflater.from(this);
        mtabhost = (FragmentTabHost)findViewById(R.id.tabhost);
        mtabhost.setup(this,getSupportFragmentManager(),R.id.viewpager);
        mtabhost.setOnTabChangedListener(this);

        int count = textviewArray.length;
        for(int i = 0;i < count;i++){//getTableItemView(i)
            TabHost.TabSpec tabSpec = mtabhost.newTabSpec(textviewArray[i]).setIndicator(getTableItemView(i));
            mtabhost.addTab(tabSpec,fragmentArray[i],null);
            mtabhost.setTag(i);
            mtabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.select_tab_background);
        }
    }

    /**
     * 初始化pageview
     */
    private void initpage(){
        HomeFragment fragment1 = new HomeFragment();
        ClassificationFragment fragment2  = new ClassificationFragment();
        NotificationFragment fragment3 = new NotificationFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),list));
        mtabhost.getTabWidget().setDividerDrawable(null);
    }

    private View getTableItemView(int i){
        Log.e("getTableItemView:"," "+i);
        View view = inflater.inflate(R.layout.tab_context,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.tab_imageview);
        TextView textView = (TextView)view.findViewById(R.id.tab_textview);
        imageView.setBackgroundResource(imageviewArray[i]);
        textView.setText(textviewArray[i]);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        Log.e("onPageScrolled:"," 666");
    }

    @Override
    public void onPageSelected(int position) {

        Log.e("onPageSelected:", " "+position);
        mtabhost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

        Log.e("ScrollStateChanged:"," "+state);
    }

    @Override
    public void onTabChanged(String tabId) {

        int position = mtabhost.getCurrentTab();
        Log.e("onTabChanged:"," "+tabId+" position"+position);
        vp.setCurrentItem(position);
    }
}
