package com.nonexistentcompany.lib;

import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;

public abstract class RouteTransformer {
    public abstract RichRoute generateRichRoute(ForeignRoute route, String origin);
}
