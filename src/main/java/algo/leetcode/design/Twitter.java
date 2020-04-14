package algo.leetcode.design;//设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个
//功能： 
//
// 
// postTweet(userId, tweetId): 创建一条新的推文 
// getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
// 
// follow(followerId, followeeId): 关注一个用户 
// unfollow(followerId, followeeId): 取消关注一个用户 
// 
//
// 示例: 
//
// 
//Twitter twitter = new Twitter();
//
//// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
//twitter.postTweet(1, 5);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//twitter.getNewsFeed(1);
//
//// 用户1关注了用户2.
//twitter.follow(1, 2);
//
//// 用户2发送了一个新推文 (推文id = 6).
//twitter.postTweet(2, 6);
//
//// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
//// 推文id6应当在推文id5之前，因为它是在5之后发送的.
//twitter.getNewsFeed(1);
//
//// 用户1取消关注了用户2.
//twitter.unfollow(1, 2);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//// 因为用户1已经不再关注用户2.
//twitter.getNewsFeed(1);
// 
// Related Topics 堆 设计 哈希表


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Twitter {
    private static int TIME = 0;
    class Feed {
        int postId;
        long timestamp;
        Feed next;

        public Feed(int postId) {
            this.postId = postId;
            this.timestamp = TIME++;
        }
    }

    // 被关注的人
    HashMap<Integer, HashSet<Integer>> followMap;
    // 推特数据
    HashMap<Integer, Feed> feedMap;

    private static PriorityQueue<Feed> maxHeap = new PriorityQueue<>((o1, o2) -> (int) (o2.timestamp - o1.timestamp));


    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        followMap = new HashMap<>();
        feedMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        Feed feed = this.feedMap.get(userId);
        if (feed == null) {
            feed = new Feed(tweetId);
            feedMap.put(userId, feed);
        } else {
            Feed head = new Feed(tweetId);
            head.next = feed;
            feedMap.put(userId, head);
        }

    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        maxHeap.clear();
        // 堆顶元素最大
        HashSet<Integer> followers = followMap.getOrDefault(userId, new HashSet<>());
        followers.add(userId);
        for (Integer user : followers) {
            Feed feed = this.feedMap.get(user);
            if (feed == null) continue;
            maxHeap.offer(feed);
        }
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        while (maxHeap.size() > 0 && cnt < 10) {
            Feed poll = maxHeap.poll();
            ans.add(poll.postId);
            if (poll.next != null)
                maxHeap.offer(poll.next);
            cnt++;
        }
        return ans;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        HashSet<Integer> floweeSet = followMap.get(followerId);
        if (floweeSet == null) {
            floweeSet = new HashSet<>();
            followMap.put(followerId, floweeSet);
        }
        floweeSet.add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> followees = followMap.get(followerId);
        if (followees == null) return;
        followees.removeIf(a -> a == followeeId);
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
//        twitter.postTweet(1, 5);
//        twitter.postTweet(1, 3);
//        twitter.postTweet(1, 10);
//        twitter.postTweet(1, 15);


//        测试用例:["Twitter","postTweet","postTweet","unfollow","follow","getNewsFeed"]
//				[[],[1,4],[2,5],[1,2],[1,2],[1]]
        twitter.postTweet(1, 4);
        twitter.postTweet(2, 5);
        twitter.follow(1, 2);

        System.out.println(twitter.getNewsFeed(1));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
//leetcode submit region end(Prohibit modification and deletion)
