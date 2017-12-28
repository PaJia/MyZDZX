package com.example.mjj.daytopnewschangetabs.base;

/**
 * Created by Administrator on 2017/11/28.
 */
public abstract class BasePresenter<M, V> {
    public M baseModel;
    public V baseView;

    public void setMV(M m, V v) {
        baseModel=m;
        baseView=v;
        this.onStart();

    }

    public abstract void onStart();
}
