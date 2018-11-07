package com.stanislaw;

/*

@title: Test.class
@author: Stanisław Woźniak
@version: 1.0

*/

public class Test {

    private int whichNumber;

    public static void main(String[] args) {

        Test test = new Test();

        try {

            test.whichNumber = Integer.parseInt(args[0]);
            if (test.whichNumber < 2) {                   //checking first argument
                throw new WrongArgException();
            }

            PrimesNumbers primesNumbers = new PrimesNumbers(test.whichNumber);

            for (int i = 1; i<args.length; i++){       //printing rest arguments with the right value
                try {
                    System.out.println(args[i] + " - "
                            + primesNumbers.number(Integer.parseInt(args[i]) - 1));

                } catch (NumberFormatException n) {
                    System.out.println(args[i] + " - wrong format");

                } catch (ArrayIndexOutOfBoundsException a){
                    System.out.println(args[i] + " - wrong number");
                }

            }
        } catch (NumberFormatException e) {
            System.out.println(args[0] + " - wrong format");
        } catch (WrongArgException w) {
            System.out.println(args[0] + " - too small number");
        } catch (ArrayIndexOutOfBoundsException a){
            System.out.println("write something!");
        }

    }
}
