package Model;

import java.util.ArrayList;

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

    public void feedBarband(int barbeyAmount, int alfalfaAmount, int strawAmount) {
        akhoor.addFeedToAkhoor(barbeyAmount, alfalfaAmount,strawAmount);
        //TODO cows must start eating
    }
}

class Akhoor {
    private int barbeyAmount;
    private int alfalfaAmount;
    private int strawAmount;

    public Akhoor() {
        barbeyAmount = 0;
        alfalfaAmount = 0;
        strawAmount = 0;
    }

    public void addFeedToAkhoor(int barbeyAmount, int alfalfaAmount, int strawAmount) {
        this.barbeyAmount += barbeyAmount;
        this.alfalfaAmount += alfalfaAmount;
        this.strawAmount += strawAmount;
    }
}