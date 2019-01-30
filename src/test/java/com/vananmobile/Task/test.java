package com.vananmobile.Task;

import java.text.DecimalFormat;

public class test {
    public static void main(String args[]) {
        double d = 2.34568;
        DecimalFormat f = new DecimalFormat("##.00");
        System.out.println(f.format(d));
    }
}
