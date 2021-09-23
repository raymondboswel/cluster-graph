package com.raymond.trimble;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    List<ClusterResource> resources = new ArrayList<>();

    Cluster(List<ClusterResource> resources) {
        this.resources = resources;
    }

    double getRate() {
        return this.resources.stream()
                .map(r -> r.resource.rate * r.transformFactor)
                .reduce(0.0, (acc, r) ->
                        acc + r
                );
    }
}
