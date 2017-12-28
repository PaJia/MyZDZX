package com.example.mjj.daytopnewschangetabs.bean;

/**
 * Created by Administrator on 2017/12/7.
 */
public class LuiBoBean {
    private String banns;
    private String banns1;
    private String banns2;

    public String getBanns() {
        return banns;
    }

    public void setBanns(String banns) {
        this.banns = banns;
    }

    public String getBanns1() {
        return banns1;
    }

    public void setBanns1(String banns1) {
        this.banns1 = banns1;
    }

    public String getBanns2() {
        return banns2;
    }

    public void setBanns2(String banns2) {
        this.banns2 = banns2;
    }

    public LuiBoBean(String banns, String banns1, String banns2) {

        this.banns = banns;
        this.banns1 = banns1;
        this.banns2 = banns2;
    }

    @Override
    public String toString() {
        return "LuiBoBean{" +
                "banns='" + banns + '\'' +
                ", banns1='" + banns1 + '\'' +
                ", banns2='" + banns2 + '\'' +
                '}';
    }
}
