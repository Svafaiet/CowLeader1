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

    public boolean isAfter(Date date) {
        if (year > date.year) {
            return true;
        } else if (year < date.year) {
            return false;
        }
        if (month > date.month) {
            return true;
        } else if (month < date.month) {
            return false;
        }
        if (day > date.day) {
            return true;
        }
        return false;

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
