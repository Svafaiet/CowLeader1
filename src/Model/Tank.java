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
        if(capacity < stock + milkAmount) {
            return false;
        }
        return true;
    }

    public void addMilk(int milkAmount, Date today) {
        if(expirationDate == null) {
            expirationDate = today.addDaytoDate(MILK_DURANCE);
        }
        stock += milkAmount;
    }
}
