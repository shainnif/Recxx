package org.recxx.compare;

// SNI Services ltd
// Shaine Ismail
// 29/07/2012


public class Compare {


    public static boolean value(String one, String two) {
        return one.equals(two);
    }


    public static boolean value(double one, double two) {
        return one == two;
    }

    public static boolean value(double one, double two, double tolerance) {
        return value((double) Math.round(one * tolerance) / tolerance
                   , (double) Math.round(two * tolerance) / tolerance);


    }
}
