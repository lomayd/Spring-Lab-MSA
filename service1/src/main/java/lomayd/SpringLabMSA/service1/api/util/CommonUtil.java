package lomayd.SpringLabMSA.service1.api.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class CommonUtil {

    public static void randomlyRunLong() {
        final int BOUND = 3;
        final long MILLIS = 11000;
        randomlyRunLong(BOUND, MILLIS);
    }

    public static void randomlyRunLong(int bound, long millis) {
        Random random = new Random();

        int randomNum = random.nextInt((bound - 1) + 1) + 1;

        if(randomNum == bound) {
            sleep(millis);
        }
    }

    private static void sleep(long millis) {
        try {
            log.info("Delay......");

            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
