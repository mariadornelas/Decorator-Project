package com.faculdade.sensor;

/**
 * Concrete Decorator: corrige um desvio sistemático conhecido do sensor
 * (ex: o hardware sempre mede 2 graus a menos que o valor real) somando
 * um offset fixo à leitura, sem o sensor original saber que isso
 * acontece.
 */
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
