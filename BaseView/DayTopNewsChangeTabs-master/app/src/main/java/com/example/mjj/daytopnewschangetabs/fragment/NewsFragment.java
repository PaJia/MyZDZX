package com.example.mjj.daytopnewschangetabs.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.bumptech.glide.Glide;
import com.example.mjj.daytopnewschangetabs.FragmentBuilder;
import com.example.mjj.daytopnewschangetabs.R;
import com.example.mjj.daytopnewschangetabs.base.BaseFragment;
import com.example.mjj.daytopnewschangetabs.bean.HeadlineBean;
import com.example.mjj.daytopnewschangetabs.net.NetContract;
import com.example.mjj.daytopnewschangetabs.net.NetModel;
import com.example.mjj.daytopnewschangetabs.net.NetPresenter;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.CubeOutTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：ViewPager切换的Fragment
 * <p/>
 * Created by Mjj on 2016/11/19.
 */
public class NewsFragment extends BaseFragment<NetPresenter, NetModel> implements NetContract.View {
    private Banner banner;
    private PullToRefreshRecyclerView recyclerView;
    private RecyclerViewHeader header;
    private ArrayList<ArrayList<String>> lists;
    private HeadlineAdapter adapter;
    private List<HeadlineBean.DataBean> data;
    private static int contentid;
    private HeadlineBean bean;
    private boolean boo = true;
    private Handler handler = new Handler();
    private int pageIndex;
    private ArrayList<String> mList = new ArrayList<>();
    private Map<String, Object> map;
    private int i = 0;

    @Override
    protected void initBan() {
        if (boo) {
            mList.add("http://upload.univs.cn/2017/1126/thumb_640_314_1511675972339.jpg");
            mList.add("http://upload.univs.cn/2017/0807/1502069949830.png");
            mList.add("http://upload.univs.cn/2017/1127/thumb_640_314_1511748883720.jpg");
            boo = false;
        }
        banner.setImages(mList)
                .setDelayTime(2000)
                .setImageLoader(new GlideImage())
                .setIndicatorGravity(BannerConfig.CENTER)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerAnimation(CubeOutTransformer.class)
                .start();

    }


    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        pageIndex = bundle.getInt("index");

        getDataByPage(pageIndex);

    }

    private void getDataByPage(int pageIndex) {
        map = new HashMap<String, Object>();
        map.put("app", "mobile");
        map.put("type", "mobile");
        map.put("catid", pageIndex);
        map.put("controller", "content");
        map.put("action", "index");
        map.put("page", ++i);
        map.put("time", "0");
        presenter.getDataModel("http://mapi.univs.cn/mobile/index.php", map);
    }

    @Override
    protected void initView(final View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        recyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.recy);
        header = (RecyclerViewHeader) view.findViewById(R.id.recyvh);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.displayLastRefreshTime(true);
        recyclerView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setRefreshComplete();
                data.addAll(data);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setLoadMoreComplete();
                        data.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void show(String res) {
        Gson gson = new Gson();
        HeadlineBean headlineBean = gson.fromJson(res, HeadlineBean.class);
        data = headlineBean.getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HeadlineAdapter(getActivity(), (ArrayList<HeadlineBean.DataBean>) data);
        recyclerView.setAdapter(adapter);
        header.attachTo(recyclerView, true);
        adapter.setOnClick(new HeadlineAdapter.OnClickItem() {
            @Override
            public void setClick(View v, int position) {
                Toast.makeText(getActivity(), "加油", Toast.LENGTH_SHORT).show();
                contentid = data.get(position).getContentid();
                Log.e("++++++++++++", contentid + "");
                FragmentBuilder.getInstance().init().add(R.id.Fragment, ShowFragment.class, false).Builder();

            }
        });
    }

    public class GlideImage extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path).into(imageView);
        }


    }

    public void setClick(setResult inter) {
        inter.setResult(contentid);
    }

    public interface setResult {
        void setResult(int str);
    }
}
