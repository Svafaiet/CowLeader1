package Model;

import Model.ReturnValues.AddToStorageReturnValue;
import Model.ReturnValues.MilkCowReturnValue;
import Model.ReturnValues.MoveCowReturnValue;
import Model.ReturnValues.SellMilkReturnValue;

import java.util.ArrayList;
import java.util.Map;

public class DairyFarm {
    private Date today;
    private ArrayList<Barband> barbands;
    private ArrayList<Tank> tanks;
    private ArrayList<CowInformation> cowInformation;
    private Storage storage;

    public DairyFarm(int year, int month, int day) {
        today = new Date(year, month, day);
        barbands = new ArrayList<>();
        tanks = new ArrayList<>();
        cowInformation = new ArrayList<>();
        storage = new Storage();
    }

    public void addNewFoodToStorage(int n, String name, int loveDegree, int weightIncrease, int milkIncrease) {
        Feed feed = new Feed(name, loveDegree, weightIncrease, milkIncrease);
        Feeds.addNewFeed(feed);
        storage.addNewFeedToStorage(feed, n);
    }

    //hasAccessToArray
    public int getCowCounts() {
        return cowInformation.size();
    }

    //hasAccessToArray
    public Barband getBarband(int n) {
        if (barbands.size() < n) {
            return null;
        } else {
            return barbands.get(n - 1);
        }
    }

    //hasAccessToArray
    public int getBarbandsCount() {
        return barbands.size();
    }

    public Storage getStorage() {
        return storage;
    }

    //hasAccessToArray
    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    //hasAccessToArray
    public Tank getTank(int n) {
        if (tanks.size() < n) {
            return null;
        }
        return tanks.get(n - 1);
    }

    public CowInformation getCowInformation(int n) {
        if(n > cowInformation.size()) {
            return CowInformation.DEAD_COW;
        }
        return cowInformation.get(n - 1);
    }

    //hasAccessToArray
    public Cow getCowByNumber(int n) {
        CowInformation cowNumberNInformation = getCowInformation(n);
        if (cowNumberNInformation.isAlive()) {
            return barbands.get(cowNumberNInformation.getBarbandNum())
                    .getCow(cowNumberNInformation.getCowNumInBarband());
        } else {
            return null;
        }
    }

    //hasAccessToArrays
    public boolean addNewCow(int n) {
        int cowNumInBarband = getBarband(n).addCow(new Cow(getCowCounts() + 1));
        if (cowNumInBarband != -1) {
            cowInformation.add(new CowInformation(n, cowNumInBarband));
            return true;
        } else {
            return false;
        }
    }

    //hasAccessToArrays
    public void addTank(int n) {
        tanks.add(new Tank(n));
    }

    private boolean isCowAlive(int n) {
        if (getCowByNumber(n) == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isCowAlive(Cow cow) {
        return cow != null;
    }

    private void killCow(int n) {
        getBarband(cowInformation.get(n).getBarbandNum()).removeCow(cowInformation.get(n).getCowNumInBarband());
        cowInformation.get(n).killCow();
    }

    //from here methods cant have access to arrays;

    public boolean feedBarband(int barbandNum, Map<String, Integer> feedsCount) {
        if (getBarbandsCount() < barbandNum) {
            return false;
        } else {
            for (String feedName : feedsCount.keySet()) {
                Feed feed = Feeds.findFeedByName(feedName);
                if(feed != null) {
                    if (storage.getFromStorage(feed, feedsCount.get(feedName))) {
                        getBarband(barbandNum).feedBarband(feed, feedsCount.get(feedName));
                    }
                }
            }
            return true;
        }
    }

    public MilkCowReturnValue milkCow(int cowNum, int tankNum) {
        Cow cow = getCowByNumber(cowNum);
        if (!isCowAlive(cow)) {
            return MilkCowReturnValue.INVALID_COW;
        }
        Tank tank = getTank(tankNum);
        if (tank == null) {
            return MilkCowReturnValue.INVALID_TANK;
        }
        int milkAmount = cow.getMilk();
        if (milkAmount == 0) {
            return MilkCowReturnValue.NO_MILK;
        }
        if (!tank.hasCapacity(milkAmount)) {
            return MilkCowReturnValue.NOT_ENOUGH_SPACE;
        }
        cow.milkCow();
        tank.addMilk(milkAmount, today);
        return MilkCowReturnValue.MILKED_SUCCESSFULLY;
    }

    public AddToStorageReturnValue addToStorage(String feedName, int amount) {
        return getStorage().addToStorage(feedName, amount);
    }

    public SellMilkReturnValue sellMilk(int milkAmount, int tankNumber) {
        Tank tank = getTank(tankNumber);
        if (tank == null) {
            return SellMilkReturnValue.INVALD_TANK;
        }
        if (tank.isMilkSpoiled(today)) {
            return SellMilkReturnValue.NOT_ENOUGH_MILK;
        } else {
            if (tank.hasMilk(milkAmount)) {
                tank.reduceMilk(milkAmount);
                return SellMilkReturnValue.MILK_SOLD_SUCCESSFULLY;
            } else {
                return SellMilkReturnValue.NOT_ENOUGH_MILK;
            }
        }
    }

    public boolean emtptyTank(int tankNumber) {
        Tank tank = getTank(tankNumber);
        if (tank == null) {
            return false;
        }
        tank.empty();
        return true;
    }

    public boolean butcherCow(int cowNumber) {
        if (isCowAlive(cowNumber)) {
            killCow(cowNumber);
            return true;
        } else {
            return false;
        }
    }

    public MoveCowReturnValue moveCow(int cowNum, int barbandNum) {
        if (!isCowAlive(cowNum)) {
            return MoveCowReturnValue.INVALID_COW;
        }
        if(barbandNum > getBarbandsCount()) {
            return MoveCowReturnValue.INVALID_BARBAND;
        }
        int cowNumInBarband = getBarband(getCowInformation(cowNum).getBarbandNum()).addCow(getCowByNumber(cowNum));
        if(cowNumInBarband == -1) {
            return MoveCowReturnValue.NOT_ENOUGH_SPACE;
        } else {
            getCowInformation(cowNum).move(barbandNum, cowNumInBarband);
            return MoveCowReturnValue.COW_MOVED_SUCCESSULLY;
        }
    }

    public void increaseStorageCapacity(int n) {
        storage.increaseStorageCapacity(n);
    }

    public void showRanks() {
    }

    //hasAccessToArrays
    public void endDay() {
        today.datePlusPlus();
        for (int i = 0; i < getCowCounts(); i++) {
            Cow cow = getCowByNumber(i);
            if(isCowAlive(cow)) {
                cow.update();
                if (cow.hasDied()) {
                    butcherCow(i);
                }
            }
            for (Barband barband : barbands) {
                barband.feedCows();
            }
        }
    }
}
