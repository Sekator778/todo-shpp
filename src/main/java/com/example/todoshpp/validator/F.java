package com.example.todoshpp.validator;

import java.util.Arrays;

/**
 * Created by user on 13 лис, 2022
 */
public class F {
    public static void main(String[] args) {
        String  brand = "Toyota";
        int price =  22500;
        boolean isSedan =  true;
        String owner = null;
    }

    public static String[] reverse(String[] a) {
        String[] result = new String[a.length];
        StringBuilder all = new StringBuilder();
        for (String s : a) {
            all.append(s);
        }
        int i = 0;
        StringBuilder reverse = all.reverse();
        for (String s : a) {
            int l = s.length();
            String add = reverse.substring(0, l);
            result[i++] = add;
            reverse.delete(0, l);
        }
        return result;
    }

}
