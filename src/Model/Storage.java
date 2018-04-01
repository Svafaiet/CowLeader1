package Model;

import Model.ReturnValues.AddToStorageReturnValue;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private int capacity;
    public Map<Feed, Integer> feedsCount;

    public Storage() {
        capacity = 0;
        feedsCount = new HashMap<>();
        feedsCount.put(Feeds.findFeedByName("barley"), 0);
        feedsCount.put(Feeds.findFeedByName("alfalfa"), 0);
        feedsCount.put(Feeds.findFeedByName("straw"), 0);
    }

    private int getStock() {
        int stock = 0;
        for (Feed feed : feedsCount.keySet()) {
            stock += feedsCount.get(feed);
        }
        return stock;
    }

    public boolean getFromStorage(Feed feed, int amount) {
        if (!feedsCount.containsKey(feed)) {
            return false;
        } else {
            if (amount > feedsCount.get(feed)) {
                return false;
            }
            feedsCount.replace(feed, feedsCount.get(feed) - amount);
            return true;
        }
    }

    public AddToStorageReturnValue addToStorage(String feedName, int amount) {
        if (amount + getStock() > capacity) {
            return AddToStorageReturnValue.NOT_ENOUGH_SPACE;
        }
        Feed feed = Feeds.findFeedByName(feedName);
        if(feed == null) {
            return AddToStorageReturnValue.NO_SUCH_A_FOOD;
        }
        feedsCount.replace(feed, feedsCount.get(feed) + amount);
        return AddToStorageReturnValue.ADDED_SUCCESSFULLY;
    }

    public AddToStorageReturnValue addNewFeedToStorage(Feed feed, int amount) {
        feedsCount.put(feed, 0);
        return addToStorage(feed.getName(), amount);
    }

    public void increaseStorageCapacity(int n) {
        capacity += n;
    }
}
