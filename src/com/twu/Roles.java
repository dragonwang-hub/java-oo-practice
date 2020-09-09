package com.twu;

import java.util.*;

public class Roles {
    private String name;
    public Roles(String name) {
        this.name = name;
    }

    static ArrayList<TrendingTopics> sortAllTopics = new ArrayList<>(); //总排序显示出来的list
    static ArrayList<TrendingTopics> topicList = new ArrayList<>(); //存放非消费topic
    static Map<Integer, TrendingTopics> buyTopicsmap = new TreeMap<>();// 存放氪金topic  treemap自带key排序，默认升序

    void viewTrendingTopic() {
        displayTopic();
    }

    void addTrendingTopic() {
        String trendingTopic = ScannerDemo.scanNextline("添加热搜话题");
        if (repeatTopic(trendingTopic)) return;
        TrendingTopics newTopic = new TrendingTopics(trendingTopic);
        topicList.add(newTopic);
    }

    protected static boolean repeatTopic(String trendingTopic) {
        if(topicList.stream().anyMatch((topic) -> topic.getTopic().equals(trendingTopic))){
            System.out.println("热搜话题重复，请重新添加！");
            return true;
        }
        if(buyTopicsmap.values().stream().anyMatch((topic) -> topic.getTopic().equals(trendingTopic))){
            System.out.println("热搜话题重复，请重新添加！");
            return true;
        }
        return false;
    }

    // 用于其他函数调用显示topics
    protected static boolean displayTopic() {
        if (topicList.isEmpty() && buyTopicsmap.isEmpty()) {
            System.out.println("请先添加热搜~");
            return false;
        } else {
            // 非消费topic的按热度排序
            topicList.sort(new TrendTopicComparator());
            sortAllTopics.clear();
            sortAllTopics.addAll(topicList);
            buyTopicsmap.forEach((k, v) -> {//sortAllTopics.add(k, v));
                if (k > sortAllTopics.size()) { // 若某个位置热搜覆盖了最后一位的热搜，其位置会因为覆盖向前移动一位。
                    sortAllTopics.add(sortAllTopics.size(), v);
                } else {
                    sortAllTopics.add(k, v);
                }
            });

            for (int i = 1; i <= sortAllTopics.size(); i++) {
                System.out.println(i + ":" + sortAllTopics.get(i - 1).getTopic() + "\t\t" + sortAllTopics.get(i - 1).getVoteCount() + "\t\t" + sortAllTopics.get(i - 1).getPrice());
            }
            return true;
        }
    }

}
