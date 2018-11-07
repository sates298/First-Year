package com.swozniak;
/*

@title: PrimesNumbers
@author: Stanisław Woźniak

*/

public class PrimesNumbers {

    private int[] primes;


    public PrimesNumbers(int n) {

        int[] tempPrimes = new int[n];       // create helping array


        int k=0;
        for(int i=2; i<=n; i++){             // finding primes numbers and their
            if(prime(i)){
                tempPrimes[k] = i;
                k++;
            }
        }

        primes = new int[k];

        for(int i=0; i < k; i++){           //rewrite primes numbers to final array
            primes[i] = tempPrimes[i];
        }

    }

    private boolean prime(int x){           //checking "primiry" number

        for(int i = 2; i*i <= x; i++){
            if(x%i == 0){
                return false;
            }
        }

        return true;
    }

    public Integer number(int m) throws ArrayIndexOutOfBoundsException{  // returning m-ty prime number
        return this.primes[m];
    }
}
