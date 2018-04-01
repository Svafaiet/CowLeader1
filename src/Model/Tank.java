package Model;

public class Tank {
    public static final int MILK_DURANCE = 3;
    private Date expirationDate;
    private int capacity;
    private int stock;

    public Tank(int capacity) {
        this.capacity = capacity;
        stock = 0;
        expirationDate = null;
    }

    public boolean hasCapacity(int milkAmount) {
        if (capacity < stock + milkAmount) {
            return false;
        }
        return true;
    }

    public boolean hasMilk(int milkAmount) {
        return stock >= milkAmount;
    }

    public void addMilk(int milkAmount, Date today) {
        if (expirationDate == null) {
            expirationDate = today.addDaytoDate(MILK_DURANCE);
        }
        stock += milkAmount;
    }

    public void reduceMilk(int amount) {
        stock -= amount;
    }

    public boolean isMilkSpoiled(Date today) {
        if (expirationDate == null) {
            return false;
        }
        return today.daysBetween(expirationDate) > MILK_DURANCE;
    }

    public void empty() {
        expirationDate = null;
        stock = 0;
    }
}
