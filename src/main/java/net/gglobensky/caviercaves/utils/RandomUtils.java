package net.gglobensky.caviercaves.utils;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static int randomRange(int min, int max){
        if (min == max && max == 0)
            return 0;

        if (min == 0)
            return random.nextInt(max);

        if (min == max)
            return max;

        return min + random.nextInt(max - min);
    }

    public static boolean randomBool(float chancePercent){
        return random.nextFloat() < chancePercent;
    }

    public static int randomFrom(int[] array){
        int index = random.nextInt(array.length);

        return array[index];
    }

}
