package com.raymond.trimble;

public class Resource {
    String code;
    String description;
    double rate;
    String unit;

    Resource(String code, String description, double rate, String unit) {
        this.code = code;
        this.description = description;
        this.rate = rate;
        this.unit = unit;
    }
}
