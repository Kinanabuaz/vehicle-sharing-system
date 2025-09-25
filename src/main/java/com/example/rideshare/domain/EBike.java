package com.example.rideshare.domain;

public final class EBike extends Vehicle {
    private final int batteryCapacityWh;
    private int currentBatteryWh;

    public EBike(String model, int batteryCapacityWh) {
        super(model);
        if (batteryCapacityWh <= 0) throw new IllegalArgumentException("batteryCapacityWh must be > 0");
        this.batteryCapacityWh = batteryCapacityWh;
        this.currentBatteryWh = batteryCapacityWh;
    }

    public int getBatteryCapacityWh() { return batteryCapacityWh; }
    public int getCurrentBatteryWh() { return currentBatteryWh; }

    public void drainBattery(int wh) {
        if (wh < 0) throw new IllegalArgumentException("wh must be >= 0");
        currentBatteryWh = Math.max(0, currentBatteryWh - wh);
    }

    @Override
    public String vehicleType() { return "EBike"; }
}
