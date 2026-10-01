package com.faculdade.sensor;

public class DecoradorCalibragem extends SensorDecorator {

    private final double offset;

    public DecoradorCalibragem(Sensor sensor, double offset) {
        super(sensor);
        this.offset = offset;
    }

    @Override
    public double ler() {
        return sensor.ler() + offset;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + String.format(" > Calibragem(%+.1f)", offset);
    }
}
