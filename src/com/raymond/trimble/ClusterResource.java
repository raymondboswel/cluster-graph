package com.raymond.trimble;

public class ClusterResource {
    Resource resource;
    int transformFactor;

    ClusterResource(Resource resource, int transformFactor) {
        this.resource = resource;
        this.transformFactor = transformFactor;
    }

    double transformedRate() {
        return this.resource.rate * this.transformFactor;
    }
}
