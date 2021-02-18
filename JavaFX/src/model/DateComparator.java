package model;

import java.util.Comparator;

public class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date d1, Date d2) {
        if (d1.getYear() > d2.getYear()){
            return 1;
        }else if(d1.getYear() < d2.getYear()){
            return -1;
        }else {
            if (d1.getMonth() > d2.getMonth()){
                return 1;
            }else if (d1.getMonth() < d2.getMonth()){
                return -1;
            }else{
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
