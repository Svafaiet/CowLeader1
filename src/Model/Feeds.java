package Model;

import java.util.HashSet;
import java.util.Set;

public class Feeds {
    private static final Set<Feed> feeds = new HashSet<>();

    static {
        feeds.add(new Feed("barley", 80, 4, 4));
        feeds.add(new Feed("alfalfa", 60, 3, 3));
        feeds.add(new Feed("straw", 20, 0, 2));
    }

    public static boolean addNewFeed(Feed feed) {
        if (feeds.contains(feed)) {
            return false;
        } else {
            feeds.add(feed);
            return true;
        }
    }

    public static Feed findFeedByName(String name) {
        for (Feed feed : feeds) {
            if(feed.equals(name)) {
                return feed;
            }
        }
        return null;
    }
}
