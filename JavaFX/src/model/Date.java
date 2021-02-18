package model;

import java.io.Serializable;

public class Date implements Serializable {

    public static final long serialVersionUID = 3089284082585951756L;

    private int day;
    private int month;
    private int year;

    private String errorMsg="";

    public Date(String date) {
        String[] dateSplit = date.split("-");
        this.day = Integer.parseInt(dateSplit[0]);
        this.month = Integer.parseInt(dateSplit[1]);
        this.year = Integer.parseInt(dateSplit[2]);
    }

    // Here, it checks the validity of the given date
    public boolean isValid(){
        if(validateDate()){
           return true;
        }else{
            return false;
        }
    }

    private boolean validateDate(){
        switch (this.month){
            //It checks the day range for the months which has 31 days.
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if(0 < this.day && this.day <= 31){
                    return true;
                }else {
                    this.errorMsg = "Day for the given month is out of range";
                    return false;
                }
            case 2:
                /*
                * Here, first it checks whether it is a leap year or not.
                * if it is a leap year, then the range of days must be between 1 and 29 inclusive.
                * else the range of days must be between 1 and 28 inclusive.
                */
                if(checkLeapYear()){
                    if(0 < this.day && this.day <= 29){
                        return true;
                    }else {
                        this.errorMsg = "Day for month February is out of range and it is a leap year.";
                        return false;
                    }
                }else {
                    if(0 < this.day && this.day <= 28){
                        return true;
                    }else {
                        this.errorMsg = "Day for month February is out of range and it isn't a leap year.";
                        return false;
                    }
                }
            //It checks the day range for the months which has 31 days.
            case 4:
            case 6:
            case 9:
            case 11:
                if(0 < this.day && this.day <= 30){
                    return true;
                }else {
                    this.errorMsg = "Day for the given month is out of range";
                    return false;
                }
            default:
                return false;
        }
    }

    // if the year is a leap year, it returns true.
    private boolean checkLeapYear(){
        if(this.year % 400 == 0){
            return true;
        }else if(this.year % 100 == 0){
            return false;
        }else if(this.year % 4 == 0){
            return true;
        }else {
            return false;
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String toString() {
        return String.format("%02d",this.day) + "-" + String.format("%02d",this.month) + "-" + this.year;
    }
}
