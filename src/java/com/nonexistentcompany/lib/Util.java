package com.nonexistentcompany.lib;

import com.nonexistentcompany.lib.domain.EULocation;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static void log(String message, Object... args) {
        System.out.println(String.format(message, args));
    }

    private static void longSleep() throws InterruptedException {
        Thread.sleep(20);
//        Thread.sleep(2000);
    }

    private static void shortSleep() throws InterruptedException {
        Thread.sleep(5);
//        Thread.sleep(500);
    }

    public static List<EULocation> simulateDriving(String license) throws InterruptedException {
        List<EULocation> locations = new ArrayList<>();
        log("Henk lives in the Eindhoven, the Netherlands.");
        longSleep();
        log("Henk starts driving...");
        longSleep();
        locations.add(new EULocation(51.4416, 5.4697));
        shortSleep();
        locations.add(new EULocation(51.4416, 5.4697, 100));
        shortSleep();
        locations.add(new EULocation(52.6416, 6.4697));
        shortSleep();
        locations.add(new EULocation(52.7416, 6.4697));
        shortSleep();
        locations.add(new EULocation(52.8416, 7.4697));
        shortSleep();
        locations.add(new EULocation(52.9416, 7.4697));
        shortSleep();
        locations.add(new EULocation(53.2416, 8.4697));
        shortSleep();
        locations.add(new EULocation(53.4416, 8.4697));
        shortSleep();

        locations.add(new EULocation(53.5511, 9.9937)); // at Hamburg now
        shortSleep();

        locations.add(new EULocation(53.4511, 8.9937));
        shortSleep();
        locations.add(new EULocation(53.3511, 8.9937));
        shortSleep();
        locations.add(new EULocation(53.2511, 7.9937));
        shortSleep();
        locations.add(new EULocation(53.1511, 6.9937));
        shortSleep();
        locations.add(new EULocation(52.5511, 6.9937));
        shortSleep();
        locations.add(new EULocation(52.2511, 6.9937));
        shortSleep();
        locations.add(new EULocation(51.4416, 5.4697));
        longSleep();
        log("Henk has stopped driving.");
        longSleep();
        log("Henk is back in the Eindhoven, the Netherlands.");
        return locations;
    }
}
