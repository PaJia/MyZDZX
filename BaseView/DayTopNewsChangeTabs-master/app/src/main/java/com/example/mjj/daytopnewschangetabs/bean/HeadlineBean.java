package com.example.mjj.daytopnewschangetabs.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */
public class HeadlineBean {


    /**
     * state : true
     * more : true
     * time : 1511850110
     * data : [{"contentid":11527,"modelid":1,"title":"共享单车的寒冬来了，你的押金还好么？","thumb":"http://upload.univs.cn/2017/1127/thumb_160_120_1511792508269.jpg","description":"随着网络系统迅速发展，共享单车逐渐进入人们的视野，可谓是达到一种\u201c泛滥\u201d的地步。而曾被市场称作是第三大共享单车品牌\u201c小蓝单车\u201d企业的倒下，共享单车再次被推到风口浪尖，你的押金还好么","comments":0,"sorttime":1511850110},{"contentid":11526,"modelid":1,"title":"加强思想政治工作的载体建设","thumb":"http://upload.univs.cn/2017/1128/thumb_160_120_1511842891675.jpg","description":"加强思想政治工作的载体建设","comments":0,"sorttime":1511850110},{"contentid":11525,"modelid":1,"title":"中国海洋大学举办第五期\u201c校长下午茶\u201d活动","thumb":"http://upload.univs.cn/2017/1128/thumb_160_120_1511843160124.jpg","description":"11月23日下午，第五期\u201c校长下午茶\u201d活动在大学生活动中心举行。中国海洋大学校长于志刚，党委常委、校长助理吴强明参加活动。","comments":0,"sorttime":1511850110},{"contentid":11522,"modelid":1,"title":"【E周青年说】第42期 人生减法，你会丢掉哪四样东西？","thumb":"http://upload.univs.cn/2017/1128/thumb_160_120_1511837685182.png","description":"我们每一个人都是第一次长大，长大过程中的每一次舍弃和每一次得到都是一次美好的经历。","comments":0,"sorttime":1511849376},{"contentid":11433,"modelid":3,"title":"千名高校优秀辅导员\u201c校园巡讲\u201d和\u201c网络巡礼\u201d活动通知","thumb":"http://upload.univs.cn/2017/1122/thumb_160_120_1511317758807.jpg","description":"用实际行动扎实推动党的十九大精神学习研究宣传全覆盖，不忘成长初心，牢记时代使命，在实现中国梦的生动实践中放飞青春梦想，在为人民利益的不懈奋斗中书写人生华章。","comments":0,"sorttime":1511764549},{"contentid":11517,"modelid":2,"title":"安徽师大学子备战考研：伴晨雾出 携星光归","thumb":"http://upload.univs.cn/2017/1127/thumb_160_120_1511762887442.jpg","description":"不知不觉，距离2017年研究生入学考试已不足一月时间，百万考研大军正蓄势待发。在学风浓郁的安徽师范大学，也有这样一群考研学子，他们伴晨雾出，携星光归，坚守在考研一线，朝着心中梦想砥砺前行。","comments":0,"sorttime":1511762912},{"contentid":11515,"modelid":1,"title":"中国海大第十四届语言文化艺术节闭幕：言语同聚 你我相伴","thumb":"http://upload.univs.cn/2017/1127/thumb_160_120_1511751051705.jpg","description":"11月24日晚，由中国海洋大学外国语学院党委、团委主办和中国海洋的大学外国语学院学生会承办的第十四届语言文化艺术节在大学生活动中心多功能厅举办。","comments":0,"sorttime":1511752634},{"contentid":11514,"modelid":1,"title":"【宪法日】宪法小常识之宪法日","thumb":"http://upload.univs.cn/2017/1127/thumb_160_120_1511751175297.jpg","description":"文章主要介绍了宪法日的时间、提出的背景、目的和历届主题。","comments":0,"sorttime":1511752595},{"contentid":11476,"modelid":1,"title":"千名高校优秀辅导员\u201c双巡活动\u201d重庆站宣讲成功举行","thumb":"http://upload.univs.cn/2017/1123/thumb_160_120_1511444416563.jpg","description":"11月23日，由教育部主办，人民网、新华网、光明网、教育部中国大学生在线等承办的\u201c学习宣传贯彻党的十九大精神\u2014\u2014千名高校优秀辅导员\u2018校园巡讲\u2019和\u2018网络巡礼\u2019重庆站\u201d活动在重庆师范大学校友会堂举行。","comments":0,"sorttime":1511489197},{"contentid":11475,"modelid":1,"title":"赣南师大：学习十九大 传诵正能量","thumb":"http://upload.univs.cn/2017/1123/thumb_160_120_1511435896546.jpg","description":"11月21日晚，以\u201c学习十九大，传播正能量\u201d为主题的青年朗诵大赛在赣南师范大学聂耳剧场举行。中华文化促进会朗读专业委员会朗诵名家、央视著名主持人现场齐助阵，吸引了众多朗诵爱好者前来欣赏。","comments":0,"sorttime":1511489196}]
     */

    private boolean state;
    private boolean more;
    private int time;
    private List<DataBean> data;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * contentid : 11527
         * modelid : 1
         * title : 共享单车的寒冬来了，你的押金还好么？
         * thumb : http://upload.univs.cn/2017/1127/thumb_160_120_1511792508269.jpg
         * description : 随着网络系统迅速发展，共享单车逐渐进入人们的视野，可谓是达到一种“泛滥”的地步。而曾被市场称作是第三大共享单车品牌“小蓝单车”企业的倒下，共享单车再次被推到风口浪尖，你的押金还好么
         * comments : 0
         * sorttime : 1511850110
         */

        private int contentid;
        private int modelid;
        private String title;
        private String thumb;
        private String description;
        private int comments;
        private int sorttime;

        public int getContentid() {
            return contentid;
        }

        public void setContentid(int contentid) {
            this.contentid = contentid;
        }

        public int getModelid() {
            return modelid;
        }

        public void setModelid(int modelid) {
            this.modelid = modelid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getSorttime() {
            return sorttime;
        }

        public void setSorttime(int sorttime) {
            this.sorttime = sorttime;
        }
    }
}
