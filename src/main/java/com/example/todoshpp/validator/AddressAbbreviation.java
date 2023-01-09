package com.example.todoshpp.validator;

/**
 * Created by user on 21 гру, 2022
 */
public class AddressAbbreviation {
    public static void main(String[] args) {
        String[] addresses = {
                "555 Straight Stave Ave, San Francisco, CA 94104",
                "444 Ave Maria Stairway St., San Francisco, CA 94104",
                "9032 Flave Steep Str, San Francisco, CA 94104"
        };

        for (String address : addresses) {
            // Replace "Ave" or "Ave." with "Avenue"
            address = address.replaceAll("Ave\\.?", "Avenue");

            // Replace "St" or "St." with "Street"
            address = address.replaceAll("St\\.?", "Street");

            // Replace "Str" or "Str." with "Street"
            address = address.replaceAll("Str\\.?", "Street");

            System.out.println(address);
        }
    }
}
