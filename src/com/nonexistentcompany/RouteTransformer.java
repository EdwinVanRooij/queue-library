package com.nonexistentcompany;

import com.nonexistentcompany.domain.RichRoute;
import com.nonexistentcompany.domain.Route;

public abstract class RouteTransformer {
    public abstract RichRoute generateRichRoute(Route route);
}
