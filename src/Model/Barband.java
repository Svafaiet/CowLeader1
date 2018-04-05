package Model;

import Controller.Separator;

import java.util.*;

public class Barband {
    private Cow[] cows;
    private Akhoor akhoor;

    Barband(int n) {
        akhoor = new Akhoor();
        cows = new Cow[n];
    }

    public Cow getCow(int n) {
        if (cows.length < n) {
            return null;
        } else {
            return cows[n - 1];
        }
    }

    public int addCow(Cow cow) {
        for (int i = 0; i < cows.length; i++) {
            if (cows[i] == null) {
                cows[i] = cow;
                return i + 1;
            }
        }
        return -1;
    }

    public void feedCows() {
        ArrayList<CowTurn> cowTurns = new ArrayList<>();
        for (int i = 0; i < cows.length; i++) {
            if (cows[i] == null) {
                continue;
            }
            cowTurns.add(new CowTurn(i, cows));
        }
        if (cowTurns.size() == 0) {
            return;
        }
        Collections.sort(cowTurns);
        while (akhoor.hasStock()) {
            for (int i = 0; i < cowTurns.size(); i++) {
                CowTurn cowTurn = cowTurns.get(i);
                if (cows[cowTurn.getIndex()].getHunger() == 0) {
                    break;
                }
                if (!akhoor.hasStock()) {
                    return;
                }
                Feed feed = akhoor.eatFromAkhoor();
                cows[cowTurn.getIndex()].feedCow(feed);
            }
            if (cows[cowTurns.get(0).getIndex()].getHunger() == 0) {
                break;
            }
        }
    }

    public void addFeedToAkhoor(Feed feed, int count) {
        akhoor.addFeedToAkhoor(feed, count);
    }

    public void removeCow(int cowNumberInBarband) {
        cows[cowNumberInBarband - 1] = null;
    }

    private String getFeedsInformation() {
        ArrayList<Feed> feeds = new ArrayList<>(akhoor.getFeedsCount().keySet());
        Collections.sort(feeds);
        int noneZeroFeedCount = 0;
        String feedInformation = "";
        for (Feed feed : feeds) {
            if (akhoor.getFeedsCount().get(feed) != 0) {
                noneZeroFeedCount++;
                feedInformation +=
                        " " + feed.getName() + Separator.SEPARATOR + akhoor.getFeedsCount().get(feed);
            }
        }
        return "" + noneZeroFeedCount + feedInformation;
    }

    public String getInformation() {
        ArrayList<Integer> cowNumbers = new ArrayList<>();
        for (Cow cow : this.cows) {
            if (cow != null) {
                cowNumbers.add(cow.getNum());
            }
        }
        Collections.sort(cowNumbers);
        String numbers = "";
        for (Integer num : cowNumbers) {
            numbers += " " + num;
        }

        return "" + cowNumbers.size() + " " + cows.length + " " + getFeedsInformation() + numbers;
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

    public Map<Feed, Integer> getFeedsCount() {
        return feedsCount;
    }

    public boolean hasStock() {
        int stock = 0;
        for (Feed feed : feedsCount.keySet()) {
            stock += feedsCount.get(feed);
        }
        return (stock > 0);
    }

    public void addFeedToAkhoor(Feed feed, int count) {
        if (!feedsCount.containsKey(feed)) {
            feedsCount.put(feed, count);
        } else {
            feedsCount.replace(feed, feedsCount.get(feed) + count);
        }
    }

    public Feed eatFromAkhoor() {
        if (!hasStock()) {
            return null;
        } else {
            ArrayList<Feed> feeds = new ArrayList<>(feedsCount.keySet());
            Collections.sort(feeds);
            for (int i = 0; i < feeds.size(); i++) {
                if (!(feedsCount.get(feeds.get(i)) == 0)) {
                    feedsCount.replace(feeds.get(i), feedsCount.get(feeds.get(i)) - 1);
                    return feeds.get(i);
                }
            }
        }
        return null;
    }
}
