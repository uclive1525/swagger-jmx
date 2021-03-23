package com.lyl.plugin.data.genereate;

import java.util.Random;

public class RandomGenerator {

    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
