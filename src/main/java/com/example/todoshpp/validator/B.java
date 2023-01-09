package com.example.todoshpp.validator;


/**
 * Created by user on 21 гру, 2022
 * Ave -> Avenue
 * Ave. -> Avenue
 * St -> Street
 * St. -> Street
 * Str -> Street
 * Str. -> Street
 */
public class B {
    public static void main(String[] args) {
/**
 * Input data:
 * 555 Straight Stave Ave, San Francisco, CA 94104
 * 444 Ave Maria Stairway St., San Francisco, CA 94104
 * 9032 Flave Steep Str, San Francisco, CA 94104
 *
 * Result:
 * 555 Straight Stave Avenue, San Francisco, CA 94104
 * 444 Ave Maria Stairway Street, San Francisco, CA 94104
 * 9032 Flave Steep Street, San Francisco, CA 94104
 */

        new B().abbreviationToFullWordUseRegexp("555 Straight Stave Ave, San Francisco, CA 94104");
        new B().abbreviationToFullWordUseRegexp("444 Ave Maria Stairway Str., San Francisco, CA 94104");
        new B().abbreviationToFullWordUseRegexp("9032 Flave Steep Str, San Francisco, CA 94104");


        new B().ab("555 Straight Stave Ave, San Francisco, CA 94104");
        new B().ab("444 Ave Maria Stairway Str., San Francisco, CA 94104");
        new B().ab("9032 Flave Steep Str, San Francisco, CA 94104");
    }

    public void abbreviationToFullWordUseRegexp(String address) {
        System.out.println(address.replaceAll("St*r*.,", "Street,")
                                 .replaceAll("Ave*.,", "Avenue,"));
    }

    public void ab(String address) {
        // Replace "Ave" or "Ave." with "Avenue"
        address = address.replaceAll("Ave\\.?", "Avenue");

        // Replace "St" or "St." with "Street"
        address = address.replaceAll("St\\.?", "Street");

        // Replace "Str" or "Str." with "Street"
        address = address.replaceAll("Str\\.?", "Street");

        System.out.println(address);
    }
}
