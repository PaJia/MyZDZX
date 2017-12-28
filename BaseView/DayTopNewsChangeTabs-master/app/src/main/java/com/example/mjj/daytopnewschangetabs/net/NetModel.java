package com.example.mjj.daytopnewschangetabs.net;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TMVPHelper on 2017/11/28
 */
public class NetModel implements NetContract.Model {
    private static NetModel netModel;
    private Set<String> keySet;
    private String urls;
    private OkHttpClient client;
    private final Handler handler;

    public NetModel() {
        client = new OkHttpClient();
        handler = new Handler();
    }

    private NetModel getNetModel() {
        if (netModel == null) {
            synchronized (NetModel.class) {
                netModel = new NetModel();
            }
        }
        return netModel;
    }


    @Override
    public void getDataFromNet(String url, final CallBacks callBacks) {
//    public void getDataFromNet(String url, Map<String, Object> map, final CallBacks callBacks) {
//        StringBuffer sb = new StringBuffer(url);
//        String urls = "";
//        if (map != null && map.size() > 0) {
//            sb.append("?");
//            keySet = map.keySet();
//            for (String key : keySet) {
//                Object o = map.get(key);
//                sb.append(key).append("=").append(o).append("&");
//            }
//
//            urls = (sb.deleteCharAt(sb.length() - 1)).toString();
//        }
//

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.succ(string);
                    }

                });
            }
        });
    }

    public void getDataNet(String url, Map<String, Object> map, final CallBacks callBacks) {
        StringBuffer sb = new StringBuffer(url);
        String urls = "";
        if (map != null && map.size() > 0) {
            sb.append("?");
            keySet = map.keySet();
            for (String key : keySet) {
                Object o = map.get(key);
                sb.append(key).append("=").append(o).append("&");
            }

            urls = (sb.deleteCharAt(sb.length() - 1)).toString();
        }

        Request request = new Request.Builder().url(urls).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.succ(string);
                    }

                });
            }
        });
    }

}