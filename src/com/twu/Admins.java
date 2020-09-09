package com.twu;

public class Admins extends Roles {
    public Admins(String na){
       super(na);
    }
    void addSuperTrenTopic() {
        String trendingTopic = ScannerDemo.scanNextline("添加超级热搜话题");
        if (repeatTopic(trendingTopic)) return;
        TrendingTopics newTopic = new TrendingTopics(trendingTopic);
        newTopic.setSuperTopic(true);
        topicList.add(newTopic);
    }
}
