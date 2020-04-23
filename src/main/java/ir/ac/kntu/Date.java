package ir.ac.kntu;

import java.util.Objects;

/**
 * @author Emad
 * @since 2020/3/20
 * @version 1.0
 */
public class Date {
    private int year;
    private int month;
    private int day;

    /**
     * @param year  Check input and construct year
     * @param month Check input and construct month
     * @param day   Check input and construct day
     */
    public Date(int year, int month, int day) {
        checkAndSetDate(year, month, day);
    }

    /**
     * @param date Constructor with Class name
     */
    public Date(Date date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
    }

    /**
     * @param year  Checks if year is bigger than 0 and set it
     * @param month Checks if month is in range of 1 and 12 and set it
     * @param day   Checks if day is in month's specific range and set it
     */
    private void checkAndSetDate(int year, int month, int day) {
        if (checkInputs(year, month, day)) {
            this.year = year;
            this.month = month;
            this.day = day;
        } else {
            this.year = 0;
            this.month = 1;
            this.day = 1;
        }
    }

    /**
     * @param year  Checks if year is bigger than 0 and set it
     * @param month Checks if month is in range of 1 and 12 and set it
     * @param day   Checks if day is in month's specific range and set it
     * @return True if parameters are in range and false otherwise
     */
    private boolean checkInputs(int year, int month, int day) {
        if (month < 1 || month > 12 || day < 1 || day > 31 || month > 6
                && day == 31) {
            return false;
        }
        return month != 12 || day != 30 || isLeapYear(year);
    }

    /**
     * @return Returns year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return Returns month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return Returns Day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day Check and set day
     */
    public void setDay(int day) {
        checkAndSetDate(this.year, this.month, day);
    }

    /**
     * @return Date as String
     */
    public String toString() {
        return year + "-" + month + "-" + day;
    }

    /**
     * @param year Get the year of Date class and checks if year is a leap year or not
     * @return True if the year is leap year and false if the year is not a leap year
     */
    private boolean isLeapYear(int year) {
        int firstFraction, secondFraction;
        final double a = 0.025d;
        final double b = 266d;
        double c, d;
        if (year > 0) {
            c = ((year + 38) % 2820) * 0.24219 + a;
            d = ((year + 39) % 2820) * 0.24219 + a;
        } else if (year < 0) {
            c = ((year + 39) % 2820) * 0.24219 + a;
            d = ((year + 40) % 2820) * 0.24219 + a;
        } else {
            return false;
        }
        firstFraction = (int) ((c - Math.floor(c)) * 1000);
        secondFraction = (int) ((d - Math.floor(d)) * 1000);
        return firstFraction <= b && secondFraction > b;
    }

    public short compare(Date expectDate){
        if(this.equals(expectDate)){
            return 0;
        }
        if(this.getYear() > expectDate.getYear()){
            return 1;
        }
        else  if(this.getYear() == expectDate.getYear() && this.getMonth() > expectDate.getMonth()){
            return 1;
        }
        else if(this.getYear() == expectDate.getYear() && this.getMonth() == expectDate.getMonth() &&
                this.getDay() > expectDate.getDay()){
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year &&
                month == date.month &&
                day == date.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}