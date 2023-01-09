package com.example.todoshpp;

import java.util.HashMap;
import java.util.Map;

public class A {
    /*


Input data:
555 Straight Stave Ave, San Francisco, CA 94104
444 Ave Maria Stairway St., San Francisco, CA 94104
9032 Flave Steep Str, San Francisco, CA 94104

Result:
555 Straight Stave Avenue, San Francisco, CA 94104
444 Ave Maria Stairway Street, San Francisco, CA 94104
9032 Flave Steep Street, San Francisco, CA 94104
    * */
    public static void main(String[] args) {
/*
Ave -> Avenue
Ave. -> Avenue
St -> Street
St. -> Street
Str -> Street
Str. -> Street*/
//        new A().abbreviationToFullWord("555 Straight Stave Ave, San Francisco, CA 94104");
//        new A().abbreviationToFullWord("444 Ave Maria Stairway St., San Francisco, CA 94104");
//        new A().abbreviationToFullWord("9032 Flave Steep Str, San Francisco, CA 94104");

       new A().count(5);
    }

    public void abbreviationToFullWord(String address) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("Ave", "Avenue");
        stringMap.put("Ave.", "Avenue");
        stringMap.put("St", "Street");
        stringMap.put("St.", "Street");
        stringMap.put("Str", "Street");
        stringMap.put("Str.", "Street");

        String[] words = address.split(" ");

        for (String word : words) {
            word = word.replace(",", "");
            if (stringMap.containsKey(word)) {
                address = address.replace(word, stringMap.get(word));
            }
        }
        System.out.println(address);
    }

    public void count(int n) {
        if (n == 0) {
            return;
        }
        count(n - 1);
        System.out.println(n);
    }

}
