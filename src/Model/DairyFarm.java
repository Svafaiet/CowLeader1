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
        if (n > cowInformation.size()) {
            return CowInformation.DEAD_COW;
        }
        return cowInformation.get(n - 1);
    }

    //hasAccessToArray
    public Cow getCowByNumber(int n) {
        CowInformation cowNumberNInformation = getCowInformation(n);
        if (cowNumberNInformation.isAlive()) {
            return getBarband(cowNumberNInformation.getBarbandNum())
                    .getCow(cowNumberNInformation.getCowNumInBarband());
        } else {
            return null;
        }
    }

    //hasAccessToArrays
    public int findNumberForCow() {
        for (int i = 0; i < getCowCounts(); i++) {
            if (!cowInformation.get(i).isAlive()) {
                return i + 1;
            }
        }
        return -1;
    }

    //hasAccessToArrays
    public int addNewCow(int n) {
        int cowNum = findNumberForCow();
        Cow newCow;
        if(cowNum == -1) {
            newCow = new Cow(getCowCounts() + 1);
        } else {
            newCow = new Cow(cowNum);
        }
        int cowNumInBarband = getBarband(n).addCow(newCow);
        if (cowNumInBarband != -1) {
            if(cowNum == -1) {
                cowInformation.add(new CowInformation(n, cowNumInBarband));
            } else {
                cowInformation.set(cowNum-1, new CowInformation(n, cowNumInBarband));
            }
            return newCow.getNum();
        } else {
            return -1;
        }
    }

    //hasAccessToArrays
    public void addTank(int n) {
        tanks.add(new Tank(n));
    }

    public void addBarband(int n) { barbands.add(new Barband(n)); }

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
        getBarband(getCowInformation(n).getBarbandNum()).removeCow(getCowInformation(n).getCowNumInBarband());
        getCowInformation(n).killCow();
    }

    private int getMaxCowProduction() {
        int ans = 0;
        for (int i = 1; i <= getCowCounts(); i++) {
            Cow cow = getCowByNumber(i);
            if (isCowAlive(cow)) {
                ans += cow.calculateMaxMilkCapacity();
            }
        }
        return ans;
    }

    public String getInformation() {
        return "" + getBarbandsCount() + " " + getCowCounts() + " " + storage.getCapacity() + " " + tanks.size() + " "
                + getMaxCowProduction();
    }

    //from here methods cant have access to arrays;

    public boolean isBarbandValid(int barbandNum) {
        return (getBarbandsCount() >= barbandNum);
    }

    public void feedBarband(int barbandNum, Map<String, Integer> feeds) {
        for (String feedName : feeds.keySet()) {
            Feed feed = Feeds.findFeedByName(feedName);
            if (feed != null) {
                if (storage.getFromStorage(feed, feeds.get(feedName))) {
                    getBarband(barbandNum).addFeedToAkhoor(feed, feeds.get(feedName));
                }
            }
        }
        getBarband(barbandNum).feedCows();

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

    public boolean emptyTank(int tankNumber) {
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
        //fixme moving cow to its barband is supported
        if (!isCowAlive(cowNum)) {
            return MoveCowReturnValue.INVALID_COW;
        }
        if (barbandNum > getBarbandsCount()) {
            return MoveCowReturnValue.INVALID_BARBAND;
        }
        if(getCowInformation(cowNum).getBarbandNum() == barbandNum) {
            return MoveCowReturnValue.COW_MOVED_SUCCESSULLY;
        }
        int cowNumInBarband = getBarband(barbandNum).addCow(getCowByNumber(cowNum));
        if (cowNumInBarband == -1) {
            return MoveCowReturnValue.NOT_ENOUGH_SPACE;
        } else {
            getBarband(getCowInformation(cowNum).getBarbandNum())
                    .removeCow(getCowInformation(cowNum).getCowNumInBarband());
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
        for (int i = 1; i <= getCowCounts(); i++) {
            Cow cow = getCowByNumber(i);
            if (isCowAlive(cow)) {
                cow.update();
                if (cow.hasDied()) {
                    butcherCow(i);
                }
            }
        }
        for (Barband barband : barbands) {
            barband.feedCows();
        }
    }
}
