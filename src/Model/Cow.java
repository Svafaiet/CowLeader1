package Model;

public class Cow {
    private int age;
    private int hunger;
    private int lastDayBeingMilked;
    private int milk;
    private int savedMilk;
    private int weight;
    private int milkedProduced;

    private boolean isCowMilkingAbilityLost() {
        return age > lastDayBeingMilked + CowPhysiology.DAYS_FOR_lOSING_MILKING;
    }

    private int calculateMaxMilkCapacity () {
        //todo maybe milk dot become zero after 3 days
        if(age < CowPhysiology.INITIAL_DAY_FOR_MILKING) {
            return 0;
        }
        if(isCowMilkingAbilityLost()) {
            return 0;
        } else {
            return CowPhysiology.INITIAL_COW_MILKING_POTENTIAL +
                    (age / CowPhysiology.DAYS_FOR_COW_PHYSIOLOGICAL_CHANGES)
                            * CowPhysiology.COW_MILKING_POTENTIAL_INCREASE_AMOUNT;
        }
    }

    public int getMilk() {
        if(isCowMilkingAbilityLost()) {
            return 0;
            //todo maybe milk dont become zero after 3 days
        }
        return milk;
    }

    public void addToMilk(int newMilk) {
        //if(isCowMilkingAbilityLost()) { return; }
        milk += newMilk;
        milk = Math.max(milk, calculateMaxMilkCapacity());
    }

    public int milkCow() {
        //todo maybe milk dont become zero after 3 days
        if(isCowMilkingAbilityLost()) {
            return 0;
        } else {
            int milkAmount = milk;
            milk = 0;
            lastDayBeingMilked = age;
            return milkAmount;
        }
    }
}

class CowPhysiology {
    public static final int INITIAL_DAY_FOR_MILKING = 1;
    public static final int COW_LIFE_LENGTH = 50;
    public static final int INITIAL_COW_DAILY_FOOD_NEED = 5;
    public static final int COW_DAILY_FOOD_NEED_INCREASE_AMOUNT = 1;
    public static final int COW_WIGHT = 200;
    public static final int DAYS_FOR_COW_PHYSIOLOGICAL_CHANGES = 10;
    public static final int INITIAL_COW_MILKING_POTENTIAL = 25;
    public static final int COW_MILKING_POTENTIAL_INCREASE_AMOUNT = 5;
    public static final int HUNGER_BOUNDARY_FOR_DEATH_MULTIPLIER = 4;
    public static final int WIGHT_BOUNDARY_FOR_DEATH_AMOUNT = 100;
    public static final int DAYS_FOR_lOSING_MILKING = 3;
}

class CowLocation {
    private int barbandNum;
    private int cowNumInBarband;

    public CowLocation(int barbandNum, int cowNumInBarband) {
        this.barbandNum = barbandNum;
        this.cowNumInBarband = cowNumInBarband;
    }

    public int getBarbandNum() {
        return barbandNum;
    }

    public int getCowNumInBarband() {
        return cowNumInBarband;
    }

    public boolean isAlive() {
        if(barbandNum == -1 || cowNumInBarband == -1) {
            return false;
        } else {
            return true;
        }
    }
}