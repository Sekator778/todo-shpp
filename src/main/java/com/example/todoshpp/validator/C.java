package com.example.todoshpp.validator;

/**
 * Task №3:
 * At the beginning of each hour for three minutes the green light is on,
 * then for one minute - yellow,
 * and then for one minute - red,
 * then again the green light is on for three minutes, and so on.
 * Enter from the keyboard a number t, which means the time in minutes elapsed since the beginning of the next hour.
 * Determine what color signal is lit for pedestrians at that moment.
 * The result is displayed in the following form:
 * "green" - if the color is green,
 * "yellow" - if the color is yellow,
 * "red" - if the color is red.
 * <p>
 * Example for number 2.5:
 * green
 * Example for number 3:
 * yellow
 * Example for number 4:
 * red
 * Example for number 5:
 * green
 */
public class C {
    public static void main(String[] args) {
        new C().colorNowLights(2.5);
        new C().colorNowLights(3);
        new C().colorNowLights(4);
        new C().colorNowLights(5);
    }

    public void colorNowLights(double time) {
        if (time >= 5) {
            time = time % 5;
        }
        if (time < 3) {
            System.out.println("green");
        } else if (time < 4) {
            System.out.println("yellow");
        } else if (time < 5) {
            System.out.println("red");
        }
    }
}
