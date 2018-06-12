package com.nonexistentcompany.lib.queue;

import com.nonexistentcompany.lib.domain.ForeignRoute;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class RouteHandler {
    public abstract void handleRoute(ForeignRoute route) throws IOException, TimeoutException;
}
