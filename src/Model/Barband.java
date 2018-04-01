package Model;

import java.util.*;

public class Barband {
    private Cow[] cows;
    private Akhoor akhoor;

    Barband(int n) {
        akhoor = new Akhoor();
        cows = new Cow[n];
    }

    public Cow getCow(int n) {
        if(cows.length > n) {
            return null;
        } else {
            return cows[n-1];
        }
    }

    public int addCow(Cow cow) {
        for (int i = 0; i < cows.length; i++) {
            if(cows[i] == null) {
                cows[i] = cow;
                return i+1;
            }
        }
        return -1;
    }

    public void feedBarband(Feed feed, int count) {
        akhoor.addFeedToAkhoor(feed, count);
        //TODO cows must start eating
    }

    public void removeCow(int cowNumberInBarband) {
        cows[cowNumberInBarband  - 1] = null;
    }
}

class Akhoor {
    private Map<Feed, Integer> feedsCount;

    public Akhoor() {
        feedsCount = new HashMap<>();
        feedsCount.put(Feeds.findFeedByName("barley"), 0);
        feedsCount.put(Feeds.findFeedByName("alfalfa"), 0);
        feedsCount.put(Feeds.findFeedByName("straw"), 0);
    }

    public void addFeedToAkhoor(Feed feed, int count) {
        if(!feedsCount.containsKey(feed)) {
            feedsCount.put(feed, count);
        } else {
            feedsCount.replace(feed, feedsCount.get(feed) + count);
        }
    }
}