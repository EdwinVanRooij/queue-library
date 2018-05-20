package com.nonexistentcompany.queue;

import com.nonexistentcompany.domain.Route;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class RouteHandler {
    public abstract void handleRoute(Route route) throws IOException, TimeoutException;
}
