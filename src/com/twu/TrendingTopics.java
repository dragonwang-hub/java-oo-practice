package com.twu;

public class TrendingTopics {
    private String topic;
    private int voteCount;
    private int price;
    private boolean superTopic;

    public String getTopic() {
        return topic;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSuperTopic() {
        return superTopic;
    }

    public void setSuperTopic(boolean superTopic) {
        this.superTopic = superTopic;
    }

    public TrendingTopics(String name) {
        this.topic = name;
        this.voteCount = 0;
        this.price = 0;
        this.superTopic = false;
    }
}

