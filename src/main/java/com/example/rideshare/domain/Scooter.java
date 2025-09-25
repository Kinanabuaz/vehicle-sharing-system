package com.example.rideshare.domain;

public final class Scooter extends Vehicle {
    private final int maxSpeedKmh;

    public Scooter(String model, int maxSpeedKmh) {
        super(model);
        if (maxSpeedKmh <= 0) throw new IllegalArgumentException("maxSpeedKmh must be > 0");
        this.maxSpeedKmh = maxSpeedKmh;
    }

    public int getMaxSpeedKmh() { return maxSpeedKmh; }

    @Override
    public String vehicleType() { return "Scooter"; }
}
