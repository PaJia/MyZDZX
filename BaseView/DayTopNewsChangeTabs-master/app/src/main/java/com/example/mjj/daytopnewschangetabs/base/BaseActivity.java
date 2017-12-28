package com.example.mjj.daytopnewschangetabs.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mjj.daytopnewschangetabs.AppApplication;
import com.example.mjj.daytopnewschangetabs.utils.TUtils;


public abstract class BaseActivity<P extends BasePresenter,M extends BaseModel> extends AppCompatActivity {
    public P presenter;
    public M model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppApplication.baseActivity=this;
        presenter= TUtils.getT(this,0);
        model=TUtils.getT(this,1);
        if (this instanceof BaseView){
            presenter.setMV(model,this);
        }
        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();


}
