package com.example.mjj.daytopnewschangetabs.net;


import com.example.mjj.daytopnewschangetabs.base.BaseModel;
import com.example.mjj.daytopnewschangetabs.base.BasePresenter;
import com.example.mjj.daytopnewschangetabs.base.BaseView;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/28.
 */
public interface NetContract {

    interface View extends BaseView {
        void show(String res);
      
    }

    interface Model extends BaseModel {
        void getDataFromNet(String url,  CallBacks callBacks);
        void getDataNet(String url, Map<String,Object> map,CallBacks callBacks);

    }

    abstract static class Presenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }
       public abstract void getDataFromModel(String url);
        public abstract void getDataModel(String url,Map<String,Object> map);
        
    }
}