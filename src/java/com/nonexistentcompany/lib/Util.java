package com.nonexistentcompany.lib;

import com.nonexistentcompany.lib.domain.EULocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * This method simulates a route:
     * Germany --> Netherlands
     * Netherlands --> Belgium
     * Belgium --> Luxembourg
     * Luxembourg --> Austria
     * Austria --> Germany
     */
    public static List<EULocation> simulateAllCountryTrip() throws InterruptedException {
        List<EULocation> locations = new ArrayList<>();

        locations.add(new EULocation(47.141401, 14.166647)); // Austria
        locations.add(new EULocation(48.165401, 14.108971)); // Austria
        locations.add(new EULocation(47.614460, 13.990368)); // Austria
        locations.add(new EULocation(48.085037, 13.609145)); // Austria

        locations.add(new EULocation(48.541396, 12.380761)); // Germany
        locations.add(new EULocation(48.720562, 11.991066)); // Germany
        locations.add(new EULocation(48.770838, 10.898228)); // Germany
        locations.add(new EULocation(49.121361, 9.627485)); // Germany
        locations.add(new EULocation(49.425362, 8.111066)); // Germany
        locations.add(new EULocation(49.623331, 6.967397)); // Germany

        locations.add(new EULocation(49.609224, 6.294690)); // Luxembourg
        locations.add(new EULocation(49.643691, 6.181335)); // Luxembourg
        locations.add(new EULocation(49.682623, 6.079546)); // Luxembourg

        locations.add(new EULocation(49.915563, 5.547471)); // Belgium
        locations.add(new EULocation(50.059839, 5.427176)); // Belgium
        locations.add(new EULocation(50.168133, 5.350835)); // Belgium
        locations.add(new EULocation(50.338236, 5.327701)); // Belgium
        locations.add(new EULocation(50.609139, 5.526651)); // Belgium
        locations.add(new EULocation(50.726439, 5.626126)); // Belgium

        locations.add(new EULocation(50.833220, 5.753361)); // Netherlands
        locations.add(new EULocation(50.898922, 5.912984)); // Netherlands

        locations.add(new EULocation(51.019863, 6.204468)); // Germany
        locations.add(new EULocation(51.095473, 6.329390)); // Germany

        return locations;
    }

    /**
     * This method simulates a route:
     * Germany --> Netherlands
     * Netherlands --> Germany
     * Germany --> Netherlands
     * Netherlands --> Germany
     */
    public static List<EULocation> simulateMultiTrip() throws InterruptedException {
        List<EULocation> locations = new ArrayList<>();
        log("Henk lives in the Eindhoven, the Netherlands.");
        longSleep();
        log("Henk starts driving...");

        locations.add(new EULocation(51.935511, 7.589731)); // Germany, munster
        locations.add(new EULocation(51.901508, 7.448841)); // Germany
        locations.add(new EULocation(51.876933, 7.145619)); // Germany
        locations.add(new EULocation(51.888277, 6.912844)); // Germany

        locations.add(new EULocation(51.956279, 6.652502)); // Netherlands
        locations.add(new EULocation(51.994013, 6.575931)); // Netherlands
        locations.add(new EULocation(52.014754, 6.456480)); // Netherlands
        locations.add(new EULocation(52.010983, 6.309464)); // Netherlands
        locations.add(new EULocation(51.948728, 6.248207)); // Netherlands

        locations.add(new EULocation(51.818279, 6.251270)); // Germany
        locations.add(new EULocation(51.759547, 6.297213)); // Germany
        locations.add(new EULocation(51.706434, 6.321715)); // Germany
        locations.add(new EULocation(51.660859, 6.321715)); // Germany
        locations.add(new EULocation(51.613336, 6.183888)); // Germany

        locations.add(new EULocation(51.601923, 5.969489)); // Netherlands
        locations.add(new EULocation(51.592410, 5.883729)); // Netherlands
        locations.add(new EULocation(51.521952, 5.877604)); // Netherlands
        locations.add(new EULocation(51.462836, 5.871478)); // Netherlands
        locations.add(new EULocation(51.392177, 5.902106)); // Netherlands
        locations.add(new EULocation(51.313752, 5.972552)); // Netherlands

        locations.add(new EULocation(51.252449, 6.208390)); // Germany
        locations.add(new EULocation(51.208054, 6.379371)); // Germany
        locations.add(new EULocation(51.191274, 6.578294)); // Germany
        locations.add(new EULocation(51.208054, 6.700708)); // Germany
        locations.add(new EULocation(51.255961, 6.861376)); // Germany

        log("Henk has stopped driving.");
        longSleep();
        log("Henk is back in the Eindhoven, the Netherlands.");
        return locations;
    }

    public static List<EULocation> simulateDriving() throws InterruptedException {
        List<EULocation> locations = new ArrayList<>();
        log("Henk lives in the Eindhoven, the Netherlands.");
        longSleep();
        log("Henk starts driving...");
        longSleep();
        locations.add(new EULocation(51.4416, 5.4697));
        shortSleep();
        locations.add(new EULocation(51.4416, 5.4697));
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
