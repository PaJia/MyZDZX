package com.example.mjj.daytopnewschangetabs.net;

import java.util.Map;

/**
* Created by TMVPHelper on 2017/11/28
*/
public class NetPresenter extends NetContract.Presenter{

    @Override
   public void getDataFromModel(String url) {
        baseModel.getDataFromNet(url, new CallBacks() {
            @Override
            public void succ(String result) {
                baseView.show(result);
            }
        });
    }

    @Override
    public void getDataModel(String url, Map<String, Object> map) {
        baseModel.getDataNet(url, map, new CallBacks() {
            @Override
            public void succ(String result) {
                baseView.show(result);
            }
        });
    }
}