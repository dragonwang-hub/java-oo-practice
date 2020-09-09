package com.twu;

import java.util.Comparator;

public class TrendTopicComparator implements Comparator<TrendingTopics> {
    @Override
    public int compare(TrendingTopics topic1, TrendingTopics topic2) {
        int result = 0;
        // 比较用来排序的两个参数。根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。
        // 再总票数排序
        int voteCountSeq = topic1.getVoteCount() - topic2.getVoteCount();
        if (voteCountSeq != 0) {
            result = (voteCountSeq < 0) ? 1 : -1;
        }
        return result;
    }
}
