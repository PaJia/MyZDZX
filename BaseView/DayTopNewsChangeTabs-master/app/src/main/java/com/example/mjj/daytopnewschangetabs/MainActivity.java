package com.example.mjj.daytopnewschangetabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mjj.daytopnewschangetabs.base.BaseActivity;
import com.example.mjj.daytopnewschangetabs.bean.LuiBoBean;
import com.example.mjj.daytopnewschangetabs.dao.ChannelItem;
import com.example.mjj.daytopnewschangetabs.dao.ChannelManage;
import com.example.mjj.daytopnewschangetabs.edit.ChannelActivity;
import com.example.mjj.daytopnewschangetabs.fragment.NewsFragment;
import com.example.mjj.daytopnewschangetabs.fragment.NewsFragmentPagerAdapter;
import com.example.mjj.daytopnewschangetabs.net.NetModel;
import com.example.mjj.daytopnewschangetabs.net.NetPresenter;
import com.example.mjj.daytopnewschangetabs.view.ColumnHorizontalScrollView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Description：仿今日头条首页tab动态添加和删除
 * <p/>
 * Created by Mjj on 2016/11/18.
 */

public class MainActivity extends BaseActivity<NetPresenter, NetModel> implements View.OnClickListener {
    private Toolbar toolbar;

    private ColumnHorizontalScrollView mColumnHorizontalScrollView; // 自定义HorizontalScrollView
    private LinearLayout mRadioGroup_content; // 每个标题

    private LinearLayout ll_more_columns; // 右边+号的父布局
    private ImageView button_more_columns; // 标题右边的+号

    private RelativeLayout rl_column; // +号左边的布局：包括HorizontalScrollView和左右阴影部分
    public ImageView shade_left; // 左阴影部分
    public ImageView shade_right; // 右阴影部分

    private int columnSelectIndex = 0; // 当前选中的栏目索引
    private int mItemWidth = 0; // Item宽度：每个标题的宽度

    private int mScreenWidth = 0; // 屏幕宽度

    public final static int CHANNELREQUEST = 1; // 请求码
    public final static int CHANNELRESULT = 10; // 返回码

    // tab集合：HorizontalScrollView的数据源
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();

    private ViewPager mViewPager;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private int pageIndex = 1;
    private ArrayList<LuiBoBean> mList = new ArrayList<>();

    private int tra = 0;

    private ListView lvLeftMenu;
    private ImageButton ivRunningMan;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter arrayAdapter;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private UMShareAPI mShareAPI;
    private TextView tv_name;
    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScreenWidth = Utils.getWindowsWidth(this);
        mItemWidth = mScreenWidth / 7; // 一个Item宽度为屏幕的1/7
        initView();
    }

    @Override
    protected void initData() {
        mList.add(new LuiBoBean("http://upload.univs.cn/2017/1126/thumb_640_314_1511675972339.jpg", "http://upload.univs.cn/2017/1114/1510638210291.jpg", "http://upload.univs.cn/2017/0619/thumb_640_314_1497839124349.jpg"));
        mList.add(new LuiBoBean("http://upload.univs.cn/2017/1126/thumb_640_314_1511675972339.jpg", "http://upload.univs.cn/2017/1114/1510638210291.jpg", "http://upload.univs.cn/2017/0619/thumb_640_314_1497839124349.jpg"));
        mList.add(new LuiBoBean("http://upload.univs.cn/2017/1127/thumb_640_314_1511748883720.jpg", "http://upload.univs.cn/2017/0927/1506480713100.png", "http://upload.univs.cn/2017/0927/thumb_640_314_1506480759630.jpg"));
        mList.add(new LuiBoBean("http://upload.univs.cn/2017/0807/1502069949830.png", "http://upload.univs.cn/2016/1122/1479797095206.png", "http://upload.univs.cn/2016/1114/thumb_640_314_1479103823901.jpg"));
        mList.add(new LuiBoBean("http://upload.univs.cn/2017/0424/1493040123828.jpg", "http://upload.univs.cn/2017/0424/thumb_640_314_1493022268406.jpg", "http://upload.univs.cn/2017/0417/thumb_640_314_1492433907753.png"));
        mList.add(new LuiBoBean("http://upload.univs.cn/2017/1127/thumb_640_314_1511748883720.jpg", "http://upload.univs.cn/2017/0927/1506480713100.png", "http://upload.univs.cn/2017/0927/thumb_640_314_1506480759630.jpg"));
    }


    protected void initView() {

//        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.too);
        toolbar.setTitle("中国大学生在线");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) findViewById(R.id.rl_column);
        button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
        shade_left = (ImageView) findViewById(R.id.shade_left);
        shade_right = (ImageView) findViewById(R.id.shade_right);

        tv_name = (TextView) findViewById(R.id.text_nc);

        ivRunningMan = (ImageButton) findViewById(R.id.iv_main);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        initDrawer();
        ivRunningMan.setOnClickListener(this);
        // + 号监听
        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_channel = new Intent(getApplicationContext(), ChannelActivity.class);
                startActivityForResult(intent_channel, CHANNELREQUEST);
            }
        });

        setChangelView();
    }

    private void initDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        lvLeftMenu.setAdapter(arrayAdapter);
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "tusoi" + position + 1, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 当栏目项发生变化时候调用
     */
    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData() {
        userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).getUserChannel());
    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = userChannelList.size();
        mColumnHorizontalScrollView.setParam(this, mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            TextView columnTextView = new TextView(this);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }

            // 单击监听
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(getApplicationContext(), userChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {

        fragments.clear();//清空
        int count = userChannelList.size();
        for (int i = 0; i < count; i++) {
            NewsFragment newfragment = new NewsFragment();
            Bundle bundle = new Bundle();
//            String mUrl = userChannelList.get(i).getUrl();
//            bundle.putString("url", mUrl);
            bundle.putInt("index", pageIndex);
            newfragment.setArguments(bundle);
            fragments.add(newfragment);
            pageIndex++;
            tra++;
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.addOnPageChangeListener(pageListener);
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        }
        //判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UMImage image = new UMImage(MainActivity.this, R.drawable.icon_app);
        switch (item.getItemId()) {
            case R.id.Menu_one:
                new ShareAction(MainActivity.this)
                        .withText("hello")
                        .withMedia(image)
                        .setPlatform(SHARE_MEDIA.QQ)
                        .setCallback(umShareListener = new UMShareListener() {
                            /**
                             * @descrption 分享开始的回调
                             * @param platform 平台类型
                             */
                            @Override
                            public void onStart(SHARE_MEDIA platform) {

                            }

                            /**
                             * @descrption 分享成功的回调
                             * @param platform 平台类型
                             */
                            @Override
                            public void onResult(SHARE_MEDIA platform) {
                                Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
                            }

                            /**
                             * @descrption 分享失败的回调
                             * @param platform 平台类型
                             * @param t 错误原因
                             */
                            @Override
                            public void onError(SHARE_MEDIA platform, Throwable t) {
                                Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            /**
                             * @descrption 分享取消的回调
                             * @param platform 平台类型
                             */
                            @Override
                            public void onCancel(SHARE_MEDIA platform) {
                                Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

                            }
                        })
                        .share();


                break;
            case R.id.Options_Three:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);//切换夜间模式
                recreate();//重新启动当前activity
                break;
            case R.id.Options_four:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);//切换日间模式
                recreate();//重新启动当前activity
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private UMShareListener umShareListener;

    private String image_url;
    private String name;
    UMAuthListener umAuthListener;

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "登录", Toast.LENGTH_SHORT).show();
        UMShareAPI.get(context).doOauthVerify(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener = new UMAuthListener() {
                    /**
                     * @desc 授权开始的回调
                     * @param platform 平台名称
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        System.out.println("====================");
                    }

                    /**
                     * @desc 授权成功的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param data 用户资料返回
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                        Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();
                        UMShareAPI.get(context).getPlatformInfo(MainActivity.this, platform, new UMAuthListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {
                                System.out.println("====================");
                            }

                            @Override
                            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> data) {
                                System.out.println("====================");
                                Set<String> set = data.keySet();
                                for (String string : set) {
                                    //设置头像
                                    if (string.equals("profile_image_url")) {

                                        image_url = data.get(string);
                                        Glide.with(MainActivity.this).load(image_url).into(ivRunningMan);
                                    }
                                    //设置昵称
                                    if (string.equals("screen_name")) {
                                        name = data.get(string);
                                        tv_name.setText(name);
                                        tv_name.setVisibility(View.VISIBLE);
                                    }
                                }
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                                System.out.println("====================");
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media, int i) {
                                System.out.println("====================");
                            }
                        });


                    }

                    /**
                     * @desc 授权失败的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        Toast.makeText(context, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @desc 授权取消的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Toast.makeText(context, "取消了", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHANNELREQUEST:
                if (resultCode == CHANNELRESULT) {
                    setChangelView();
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
