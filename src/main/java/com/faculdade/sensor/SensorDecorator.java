package com.faculdade.sensor;

import java.util.Objects;

public abstract class SensorDecorator implements Sensor {

    protected final Sensor sensor;

    protected SensorDecorator(Sensor sensor) {
        this.sensor = Objects.requireNonNull(sensor, "sensor não pode ser nulo");
    }

    @Override
    public double ler() {
        return sensor.ler();
    }

    @Override
    public String getDescricao() {
        return sensor.getDescricao();
    }
}
