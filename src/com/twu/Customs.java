package com.twu;

public class Customs extends Roles {
    public Customs(String na) {
        super(na);
    }

    void voteTrendingTopic() {
        if (displayTopic()) {
            int seqNumb = borderJudge("选择一个热搜话题投票：");

            int votesNumb;
            while (true) {
                votesNumb = ScannerDemo.scanNextInt("你想投几票呢？（最多10票！）");
                if (votesNumb > 10 || votesNumb < 0) {
                    System.out.println("投票数错误,请重新输入！");
                } else {
                    break;
                }
            }
            /* 判断是否为超级热搜，是则加2倍投票 */
            if (sortAllTopics.get(seqNumb - 1).isSuperTopic()) {
                votesNumb *= 2;
            }
            // 更新后票总数先确定
            int newVoteCount = sortAllTopics.get(seqNumb - 1).getVoteCount() + votesNumb;

            TrendingTopics curTopic = sortAllTopics.get(seqNumb - 1);
            if (topicList.contains(curTopic)) { // 如果在topiclist列表中
                // addAll是浅拷贝，总list的元素变，topiclist元素也会跟着变
                sortAllTopics.get(seqNumb - 1).setVoteCount(newVoteCount);
                return;
            }

            // 若在topicmap中
            buyTopicsmap.keySet().forEach((key) -> {
                if (buyTopicsmap.get(key).equals(curTopic)) {
                    buyTopicsmap.get(key).setVoteCount(newVoteCount);// map中的元素的票数更新
                }
            });
        }
    }

    void buyTrendingTopic() {
        if (displayTopic()) {
            int seqNumb = borderJudge("选择一个热搜话题进行氪金操作：");
            int toSeqNumb = borderJudge("设置该热搜为第几位？");
            int priceNumb;
            while (true) {
                priceNumb = ScannerDemo.scanNextInt("你消费投资金额？");
                if (priceNumb <= 0) {
                    System.out.println("金额错误,请重新输入！");
                } else {
                    break;
                }
            }
            TrendingTopics newBuyTopic = sortAllTopics.get(seqNumb - 1);
            if (buyTopicsmap.containsKey(toSeqNumb - 1)) { // 氪金topic表中已存在购买此位置
                if (priceNumb <= buyTopicsmap.get(toSeqNumb - 1).getPrice()) { // 若价格小于等于oldbuytopic的price
                    System.out.println("购买失败！请检查！");
                    return;
                }
            }
            newBuyTopic.setPrice(priceNumb);
            // 如果在topiclist列表中 执行，不在则跳过这句
            topicList.remove(newBuyTopic);
            // 若在topicmap中
            for (int key : buyTopicsmap.keySet()) {
                if (buyTopicsmap.get(key).equals(newBuyTopic)) {
                    buyTopicsmap.remove(key);// map中的元素更新:即更新key值
                    break;
                }
            }
            buyTopicsmap.put(toSeqNumb - 1, newBuyTopic);
        }//if (true)
    }


    // 用于判断seqnumb读入值是否在 topic列表中
    private int borderJudge(String promt) {
        int seqNumb;
        while (true) {
            seqNumb = ScannerDemo.scanNextInt(promt);
            if (seqNumb > sortAllTopics.size() || seqNumb <= 0) {
                System.out.println("选择错误,请重新选择！");
            } else {
                break;
            }
        }
        return seqNumb;
    }

}//public class Roles
