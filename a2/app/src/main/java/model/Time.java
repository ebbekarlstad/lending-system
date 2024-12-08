package model;

/**
 * Represents a simplified time model with a year, month, and day.
 * The class provides methods for incrementing time, comparing two time instances, 
 * and calculating the difference in days. This implementation assumes each month has 30 days.
 */
public class Time {
  private int year;
  private int month;
  private int day;

  /**
   * Constructs a Time object with the specified year, month, and day.
   *
   * @param year   the year value
   * @param month  the month value (1-12)
   * @param day    the day value (1-30)
   */
  public Time(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  /**
   * Copy constructor that creates a new Time object by copying the values from another Time object.
   *
   * @param other the Time object to copy
   */
  public Time(Time other) {
    this.year = other.year;
    this.month = other.month;
    this.day = other.day;
  }

  /**
   * Increments the day by one. If the day exceeds 30, it rolls over to the next month.
   * If the month exceeds 12, it rolls over to the next year.
   * Assumes each month has 30 days.
   */
  public void increment() {
    day++;
    if (day > 30) { // Simplified month length
      day = 1;
      month++;
      if (month > 12) {
        month = 1;
        year++;
      }
    }
  }

  /**
   * Compares if this Time object represents a date after the specified other Time object.
   *
   * @param other the Time object to compare against
   * @return true if this Time is after the other Time, false otherwise
   */
  public boolean isAfter(Time other) {
    if (this.year != other.year) {
      return this.year > other.year;
    }
    if (this.month != other.month) {
      return this.month > other.month;
    }
    return this.day >= other.day;
  }

  /**
   * Calculates the absolute difference in days between this Time object and another Time object.
   * Assumes each month has 30 days and each year has 360 days.
   *
   * @param other the Time object to compare against
   * @return the absolute difference in days between this and the other Time
   */
  public int differenceInDays(Time other) {
    // Simplified calculation, assumes each month has 30 days
    int thisDays = this.year * 360 + this.month * 30 + this.day;
    int otherDays = other.year * 360 + other.month * 30 + other.day;
    return Math.abs(thisDays - otherDays);
  }

  /**
   * Increments the current date by a specified number of days.
   *
   * @param putDays the number of days to add; must be a positive integer.
   *                No change occurs if putDays is zero or negative.
   */
  public void addDays(int putDays) {
    while (putDays > 0) {
      increment();
      putDays--;
    }
  }

  // Getters
  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

}
