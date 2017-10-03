package com.snhu.attendu.attendu;

import java.util.Random;

/**
 * Created by edward.greer on 10/3/2017.
 */

public class GenerateCode {
    int min= 10000;
    int max = 99999;


    Random rnd = new Random();
    int Ccode= rnd.nextInt(max-min+1)+min;
}
