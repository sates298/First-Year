package com.stanislaw;

/*@title: main
@author: Stanisław Woźniak
@version: 1.0*/

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("write something!");
        } else {
            int x;
            for (String s : args) {
                try {
                    x = Integer.parseInt(s);
                    System.out.println(s + " - " + RomArab.arabToRom(x));   //convert arabic to roman

                } catch (RomArabException e) {

                    System.out.println(s + " - " + e.getMessage());

                } catch (NumberFormatException n) {
                    try {
                        System.out.println(s + " - " + RomArab.romToArab(s));  //convert roman to arabic

                    } catch (RomArabException r) {
                        System.out.println(s + " - " + r.getMessage());
                    }
                }
            }
        }
    }
}
