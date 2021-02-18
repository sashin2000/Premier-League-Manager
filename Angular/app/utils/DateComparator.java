package utils;

import models.Date;

import java.util.Comparator;

/*
* This class is DateComparator
* This class is used to compare dates in ascending order
*
* Last modified date - 25/12/2020
*/
public class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date d1, Date d2) {
        if (d1.getYear() > d2.getYear()){
            return 1;
        }else if(d1.getYear() < d2.getYear()){
            return -1;
        }else { //If years are equal, execute this block
            if (d1.getMonth() > d2.getMonth()){
                return 1;
            }else if (d1.getMonth() < d2.getMonth()){
                return -1;
            }else{  //If months are equal, execute this block
                if(d1.getDay() > d2.getDay()){
                    return 1;
                }else if(d1.getDay() < d2.getDay()){
                    return -1;
                }else {
                    return 0;
                }
            }
        }

    }
}
