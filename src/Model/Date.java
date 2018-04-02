package Model;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date clone() {
        return new Date(year, month, day);
    }

    public int daysBetween(Date anotherDate) {
        return Math.abs((this.year - anotherDate.year) * 360 +
                (this.month - anotherDate.month) * 30 +
                (this.day - anotherDate.day));
    }

    public Date addDaytoDate(int dayCount) {
        Date ans = this.clone();
        ans.day += dayCount;
        ans.month += (ans.day / 30);
        ans.year += (ans.month / 12);
        ans.day %= 30;
        ans.month %= 12;
        return ans;
    }

    public void datePlusPlus() {
        day++;
        month += (day / 30);
        year += (month / 12);
        day %= 30;
        month %= 12;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }
}
