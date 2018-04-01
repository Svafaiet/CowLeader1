package Model;

import Model.ReturnValues.MilkCowReturnValue;

import java.util.ArrayList;

public class DairyFarm {
    private Date today;
    private ArrayList<Barband> barbands;
    private ArrayList<Tank> tanks;
    private ArrayList<CowLocation> cowInformation;
    private Storage storage;

    public DairyFarm(int year, int month, int day) {
        today = new Date(year, month, day);
        barbands = new ArrayList<>();
        tanks = new ArrayList<>();
        cowInformation = new ArrayList<>();
        storage = new Storage();
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

    //hasAccessToArray
    public Cow getCowByNumber(int n) {
        if (n > cowInformation.size()) {
            return null;
        }
        if (cowInformation.get(n - 1).isAlive()) {
            return barbands.get(cowInformation.get(n - 1).getBarbandNum())
                    .getCow(cowInformation.get(n - 1).getCowNumInBarband());
        } else {
            return null;
        }
    }

    //hasAccessToArrays
    public boolean addNewCow(int n) {
        int cowNumInBarband = getBarband(n).addCow(new Cow());
        if (cowNumInBarband != -1) {
            cowInformation.add(new CowLocation(n, cowNumInBarband));
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

    //from here methods cant have access to arrays;

    public boolean feedBarband(int n, int barbeyAmount, int alfalfaAmount, int strawAmount) {
        if (getBarbandsCount() < n) {
            return false;
        } else {
            getBarband(n).feedBarband(barbeyAmount, alfalfaAmount, strawAmount);
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
        return  MilkCowReturnValue.MILKED_SUCCESSFULLY;
    }
}
