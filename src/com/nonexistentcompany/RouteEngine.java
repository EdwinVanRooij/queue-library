package com.nonexistentcompany;

import java.util.*;

import static com.nonexistentcompany.Util.log;
import static com.nonexistentcompany.Util.longSleep;
import static com.nonexistentcompany.Util.shortSleep;

public class RouteEngine {

    public List<Location> simulateDriving() throws InterruptedException {
        List<Location> locations = new ArrayList<>();
        log("Henk lives in the Eindhoven, the Netherlands.");
        longSleep();
        log("Henk starts driving...");
        longSleep();
        locations.add(new Location(51.4416, 5.4697));
        shortSleep();
        locations.add(new Location(51.4416, 5.4697, 100));
        shortSleep();
        locations.add(new Location(52.6416, 6.4697));
        shortSleep();
        locations.add(new Location(52.7416, 6.4697));
        shortSleep();
        locations.add(new Location(52.8416, 7.4697));
        shortSleep();
        locations.add(new Location(52.9416, 7.4697));
        shortSleep();
        locations.add(new Location(53.2416, 8.4697));
        shortSleep();
        locations.add(new Location(53.4416, 8.4697));
        shortSleep();

        locations.add(new Location(53.5511, 9.9937)); // at Hamburg now
        shortSleep();

        locations.add(new Location(53.4511, 8.9937));
        shortSleep();
        locations.add(new Location(53.3511, 8.9937));
        shortSleep();
        locations.add(new Location(53.2511, 7.9937));
        shortSleep();
        locations.add(new Location(53.1511, 6.9937));
        shortSleep();
        locations.add(new Location(52.5511, 6.9937));
        shortSleep();
        locations.add(new Location(52.2511, 6.9937));
        shortSleep();
        locations.add(new Location(51.4416, 5.4697));
        longSleep();
        log("Henk has stopped driving.");
        longSleep();
        log("Henk is back in the Eindhoven, the Netherlands.");
        return locations;
    }
}
