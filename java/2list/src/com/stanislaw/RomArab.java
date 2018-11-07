package com.stanislaw;

/*@title: RomArab
@author: Stanisław Woźniak
@version: 1.0*/

public class RomArab {

    private static String[] numbers = {"I", "V", "X", "L", "C", "D", "M"};


    public static int romToArab(String rome) throws RomArabException {   //changing Roman numbers to arabic
        int arab = 0;
        int i = 0;
        boolean[] checking = new boolean[13];  //checking
        for (int j = 0; j< 13; j++) {
            checking[j] = true;
        }
        int howmanyM = 0;           //variables to counting charachters
        int howmanyC = 0;
        int howmanyX = 0;
        int howmanyI = 0;

        rome = rome + "0";
        while ((rome.length() - 1) > i) {

            if (rome.charAt(i) == numbers[4].charAt(0) &&
                    rome.charAt(i + 1) == numbers[6].charAt(0) && checking[1]) {// CM to 900
                arab = arab + 900;
                i++;

                checking[0] = checking[1] = checking[2] = checking[3] = checking[4] = false;

            } else if (rome.charAt(i) == numbers[6].charAt(0) && checking[0]) {  //M to 1000
                arab = arab + 1000;
                howmanyM++;
                if( howmanyM == 3){
                    checking[0] = false;
                }
            } else if (rome.charAt(i) == numbers[4].charAt(0) &&
                    rome.charAt(i + 1) == numbers[5].charAt(0) && checking[2]) {  //CD to 400
                arab = arab + 400;
                i++;

                checking[0] = checking[1] = checking[2] = checking[3] = checking[4] = false;

            } else if (rome.charAt(i) == numbers[5].charAt(0) && checking[3]) {  //D to 500
                arab = arab + 500;

                checking[0] = checking[1] = checking[2] = checking[3] = false;

            } else if (rome.charAt(i) == numbers[4].charAt(0) && checking[4]) {  //C to 100
                arab = arab + 100;

                checking[0] = checking[1] = checking[2] = checking[3] = false;

                howmanyC++;
                if(howmanyC == 3){
                    checking[4] = false;
                }
            } else if (rome.charAt(i) == numbers[2].charAt(0) &&
                    rome.charAt(i + 1) == numbers[4].charAt(0) && checking[5]) {  //XC to 90
                arab = arab + 90;

                checking[0] = checking[1] = checking[2] = checking[3] =
                        checking[4] = checking[5] = checking[6] = checking[7] = checking[8] = false;

                i++;
            } else if (rome.charAt(i) == numbers[2].charAt(0) &&
                    rome.charAt(i + 1) == numbers[3].charAt(0) && checking[6]) {  //XL to 40
                arab = arab + 40;
                i++;

                checking[0] = checking[1] = checking[2] = checking[3] =
                        checking[4] = checking[5] = checking[6] = checking[7] = checking[8] = false;

            } else if (rome.charAt(i) == numbers[3].charAt(0) && checking[7]) {  //L to 50
                arab = arab + 50;

                checking[0] = checking[1] = checking[2] = checking[3] =
                        checking[4] = checking[5] = checking[6] = checking[7] = false;

            } else if (rome.charAt(i) == numbers[2].charAt(0) && checking[8]) {  //X to 10
                arab = arab + 10;
                howmanyX++;
                if(howmanyX == 3){
                    checking[8] = false;
                }

                checking[0] = checking[1] = checking[2] = checking[3] =
                        checking[4] = checking[5] = checking[6] = checking[7] = false;

            } else if (rome.charAt(i) == numbers[1].charAt(0) && checking[9]) {  //V to 5
                arab = arab + 5;
                for(int j = 0; j < 12; j++){
                    checking[j] = false;
                }
            } else if (rome.charAt(i) == numbers[0].charAt(0) &&
                    rome.charAt(i + 1) == numbers[1].charAt(0) && checking[10]) {  //IV to 4
                arab = arab + 4;
                i++;

                for(int j = 0; j < 13; j++){
                    checking[j] = false;
                }

            } else if (rome.charAt(i) == numbers[0].charAt(0) &&
                    rome.charAt(i + 1) == numbers[2].charAt(0) && checking[11]) {  //IX to 9
                arab = arab + 9;
                i++;
                for(int j = 0; j < 13; j++){
                    checking[j] = false;
                }
            } else if (rome.charAt(i) == numbers[0].charAt(0) && checking[12]) {  //I to 1
                arab = arab + 1;
                howmanyI++;
                if(howmanyI == 3){
                    checking[12] = false;
                }
                for(int j = 0; j < 12; j++){
                    checking[j] = false;
                }
            } else {
                throw new RomArabException("It's not roman number");
            }
            i++;
        }

        return arab;
    }

    public static String arabToRom(int arab) throws RomArabException {  //changing arabic number to roman
        String rome = "";
        int temp = arab;

        if (arab > 3999 || arab < 1) {
            throw new RomArabException("wrong number");
        } else {

            for (int i = 0; i < temp / 1000; i++) {  //3000, 2000, 1000 to MMM, MM, M
                rome = rome + numbers[6];
            }
            temp = temp % 1000;

            if (temp / 100 == 9) {
                rome = rome + numbers[4] + numbers[6];  //900 to CM
                temp = temp - 900;
            }

            if (temp / 100 >= 5) {
                rome = rome + numbers[5];  //500 to D
                temp = temp - 500;
            }

            if (temp / 100 == 4) {
                rome = rome + numbers[4] + numbers[5]; //400 to CD
                temp = temp - 400;
            }

            for (int i = 0; i < temp / 100; i++) {  //100,200,300 to C, CC, CCC
                rome = rome + numbers[4];
            }

            temp = temp % 100;

            if (temp / 10 == 9) {
                rome = rome + numbers[2] + numbers[4]; //90 to XC
                temp = temp - 90;
            }

            if (temp / 10 >= 5) {
                rome = rome + numbers[3]; //50 to L
                temp = temp - 50;
            }

            if (temp / 10 == 4) {
                rome = rome + numbers[2] + numbers[3]; //40 to XL
                temp = temp - 40;
            }

            for (int i = 0; i < temp / 10; i++) {
                rome = rome + numbers[2]; //30,20,10 to XXX, XX, X
            }

            temp = temp % 10;

            if (temp == 9) {
                rome = rome + numbers[0] + numbers[2]; //9 to IX
                temp = 0;
            }

            if (temp >= 5) {
                rome = rome + numbers[1];  // 5 to V
                temp = temp - 5;
            }

            if (temp == 4) {
                rome = rome + numbers[0] + numbers[1]; //4 to IV
                temp = 0;
            }

            while (temp != 0) {
                rome = rome + numbers[0];  //3,2,1 to III, II, I
                temp--;
            }
        }

        return rome;
    }

}
