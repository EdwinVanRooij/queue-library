package com.nonexistentcompany.examples.germany;

import com.nonexistentcompany.lib.RouteTransformer;
import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;
import com.nonexistentcompany.lib.domain.RichRouteDetail;

import java.util.ArrayList;
import java.util.List;

public class RouteTransformerGermany extends RouteTransformer {

    @Override
    public RichRoute generateRichRoute(ForeignRoute route) {
        List<RichRouteDetail> details = new ArrayList<>();
        return new RichRoute(route.getId(), 20.0, 10000, 21, details);
    }
}
