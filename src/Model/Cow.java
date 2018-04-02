package Model;

public class Cow {
    private int num;
    private int age;
    private int hunger;
    private int lastDayBeingMilked;
    private int milk;
    private int savedMilk;
    private int weight;
    private int totalMilkProduced;

    public Cow(int num) {
        this.num = num;
        age = 0;
        hunger = 0;
        lastDayBeingMilked = 0;
        milk = 0;
        savedMilk = 0;
        weight = CowPhysiology.COW_WIGHT;
        totalMilkProduced = 0;
    }

    private boolean isCowMilkingAbilityLost() {
        return age > lastDayBeingMilked + CowPhysiology.DAYS_FOR_LOSING_MILKING;
    }

    public int calculateMaxMilkCapacity() {
        if (age < CowPhysiology.INITIAL_DAY_FOR_MILKING) {
            return 0;
        }
        if (isCowMilkingAbilityLost()) {
            return 0;
        } else {
            return CowPhysiology.INITIAL_COW_MILKING_POTENTIAL +
                    (int) Math.floor(age / CowPhysiology.DAYS_FOR_COW_PHYSIOLOGICAL_CHANGES)
                            * CowPhysiology.COW_MILKING_POTENTIAL_INCREASE_AMOUNT;
        }
    }

    private int calculateCowDailyFoodNeed() {
        return CowPhysiology.INITIAL_COW_DAILY_FOOD_NEED +
                (int) Math.floor(age / CowPhysiology.DAYS_FOR_COW_PHYSIOLOGICAL_CHANGES)
                        * CowPhysiology.COW_DAILY_FOOD_NEED_INCREASE_AMOUNT;
    }

    public int getMilk() {
        return milk;
    }

    public int getNum() {
        return num;
    }

    public int getAge() {
        return age;
    }

    public int getHunger() {
        return hunger;
    }

    public void feedCow(Feed feed) {
        if (age < CowPhysiology.INITIAL_DAY_FOR_MILKING) {
            return;
        }
        savedMilk += feed.getMilkIncrease();
        weight += feed.getWeightIncrease();

    }

    public void milkCow() {
        if(isCowMilkingAbilityLost()){
            return;
        }
        milk = 0;
        lastDayBeingMilked = age;
        totalMilkProduced += milk;
    }

    public void update() {
        age++;
        milk += savedMilk;
        savedMilk = 0;
        milk = Math.min(milk, calculateMaxMilkCapacity());
        //maybe order change
        weight -= hunger;
        hunger += calculateCowDailyFoodNeed();
        if (isCowMilkingAbilityLost()) {
            milk = 0;
        }
    }

    public boolean hasDied() {
        if (age > CowPhysiology.COW_LIFE_LENGTH) {
            return true;
        }
        if (weight < CowPhysiology.WIGHT_BOUNDARY_FOR_DEATH_AMOUNT) {
            return true;
        }
        if (hunger >= calculateCowDailyFoodNeed() * CowPhysiology.HUNGER_BOUNDARY_FOR_DEATH_MULTIPLIER) {
            return true;
        }
        return false;
    }

    public String getInformation() {
        return "" + num + " " + age + " " + hunger + " " + weight + " " + milk + " " + totalMilkProduced;
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
    public static final int DAYS_FOR_LOSING_MILKING = 3;
}

class CowInformation {
    public static final CowInformation DEAD_COW = new CowInformation(-1, -1);
    private int barbandNum;
    private int cowNumInBarband;

    public CowInformation(int barbandNum, int cowNumInBarband) {
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
        if (barbandNum == -1 || cowNumInBarband == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void killCow() {
        barbandNum = DEAD_COW.barbandNum;
        cowNumInBarband = DEAD_COW.cowNumInBarband;
    }

    public void move(int barbandNum, int cowNumInBarband) {
        this.barbandNum = barbandNum;
        this.cowNumInBarband = cowNumInBarband;
    }
}

class CowTurn implements Comparable<CowTurn> {
    private int hunger;
    private int num;
    private int age;
    private int index;

    public CowTurn(int index, Cow[] cows) {
        this.index = index;
        hunger = cows[index].getHunger();
        num = cows[index].getNum();
        age = cows[index].getAge();
    }

    public int compareTo(CowTurn cowTurn) {
        if (this.hunger < cowTurn.hunger) {
            return 1;
        }
        if (this.hunger > cowTurn.hunger) {
            return -1;
        }
        if (this.age > cowTurn.age) {
            return 1;
        }
        if (this.age < cowTurn.age) {
            return -1;
        }
        return Integer.compare(this.num, cowTurn.num);

    }

    public int getIndex() {
        return index;
    }
}