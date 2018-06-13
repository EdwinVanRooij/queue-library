package com.nonexistentcompany.examples.germany;

import com.nonexistentcompany.lib.RouteTransformer;
import com.nonexistentcompany.lib.domain.RichRoute;
import com.nonexistentcompany.lib.domain.ForeignRoute;
import com.nonexistentcompany.lib.domain.RichRouteDetail;

import java.util.ArrayList;
import java.util.List;

public class RouteTransformerGermany extends RouteTransformer {

    @Override
    public RichRoute generateRichRoute(ForeignRoute route, String ownCountry) {
        List<RichRouteDetail> details = new ArrayList<>();
        details.add(new RichRouteDetail(0.003, "It's a kind of magic.", 1000000L, 1000001L));
        details.add(new RichRouteDetail(0.002, "It's a kind of magic numbah two.", 1000003L, 1000004L));
        return new RichRoute(route.getId(), ownCountry, 20.0, 10000, 21, details);
    }
}
